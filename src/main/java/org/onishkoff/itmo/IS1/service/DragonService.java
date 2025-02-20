package org.onishkoff.itmo.IS1.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.criteria.Predicate;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.onishkoff.itmo.IS1.dto.model.request.DragonDtoRequest;
import org.onishkoff.itmo.IS1.dto.model.response.DragonDto;
import org.onishkoff.itmo.IS1.exception.DragonNotFoundException;
import org.onishkoff.itmo.IS1.model.*;
import org.onishkoff.itmo.IS1.repository.DragonRepository;
import org.onishkoff.itmo.IS1.util.Mapper;
import org.onishkoff.itmo.IS1.util.SecurityUtil;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.net.ConnectException;
import java.sql.SQLException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DragonService {

    private final Validator validator;
    private final ObjectMapper objectMapper;
    private final DragonRepository dragonRepository;
    private final Mapper mapper;
    private final SecurityUtil securityUtil;
    private final PersonService personService;
    private final CoordinateService coordinateService;
    private final DragonCaveService dragonCaveService;
    private final DragonHeadService dragonHeadService;
    private final ObjectStorageService objectStorageService;
    private final UploadHistoryService uploadHistoryService;


    public DragonDto getDragonDto(int id) {
        return mapper.toDragonDto(dragonRepository.findById(id).orElseThrow());
    }

    public Dragon getDragon(Integer id) {
        return dragonRepository.findById(id).orElseThrow(DragonNotFoundException::new);
    }


    public Page<DragonDto> getDragons(Integer page, Integer size, String sortColumn, String filter, String order, Boolean userPersonOnly) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sortColumn));
        Specification<Dragon> specification = (root, query, criteriaBuilder) -> {
            Predicate userOnlyPredicate = criteriaBuilder.disjunction();
            Predicate filterPredicate = criteriaBuilder.conjunction();
            if (filter != null && !filter.isEmpty()) {
                filterPredicate = criteriaBuilder.like(root.get("name"), "%" + filter + "%");
            }
            if (userPersonOnly) {
                userOnlyPredicate = criteriaBuilder.equal(root.get("owner"), securityUtil.getUserFromContext());
            } else {
                return filterPredicate;
            }
            return criteriaBuilder.and(filterPredicate, userOnlyPredicate);
        };
        Page<Dragon> rawPages = dragonRepository.findAll(specification, pageable);
        return rawPages.map(mapper::toDragonDto);
    }


    @Retryable(value = CannotAcquireLockException.class, backoff = @Backoff(delay = 1000, multiplier = 2))
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.SERIALIZABLE)
    public DragonDto createDragon(DragonDtoRequest dto) {
        dto.setOwner(securityUtil.getUserFromContext());
        if (dto.getDragonHead() != null && dto.getDragonHead().getToothCount() != null) {
            dto.getDragonHead().setId(null);
        }

        Dragon toUpdate = mapper.toDragon(dto);
        if(dto.getPerson() != null){
            toUpdate.setPerson(personService.findPersonById(dto.getPerson().getId()));
        }
        Dragon dragon = dragonRepository.save(toUpdate);
        return mapper.toDragonDto(dragon);
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.SERIALIZABLE)
    public void delete(Integer id) {
        dragonRepository.deleteById(id);
    }


    @Retryable(value = {CannotAcquireLockException.class},
            backoff = @Backoff(delay = 1000, multiplier = 2))
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.SERIALIZABLE)
    public DragonDto update(DragonDtoRequest dragonDto) {
        Coordinates coordinates = coordinateService.findCoordinatesById(dragonDto.getCoordinates().getId());
        coordinates.setX(dragonDto.getCoordinates().getX());
        coordinates.setY(dragonDto.getCoordinates().getY());

        DragonCave dragonCave = null;
        if (dragonDto.getDragonCave() != null) {
            dragonCave = dragonDto.getDragonCave().getId() == null ?
                    new DragonCave() :
                    dragonCaveService.findDragonCaveById(dragonDto.getDragonCave().getId());
            dragonCave.setDepth(dragonDto.getDragonCave().getDepth());
            dragonCave.setNumberOfTreasures(dragonDto.getDragonCave().getNumberOfTreasures());
        }

        DragonHead dragonHead = dragonHeadService.findById(dragonDto.getDragonHead().getId());
        dragonHead.setToothCount(dragonDto.getDragonHead().getToothCount());

        Dragon dragonToUpdate = dragonRepository.findById(dragonDto.getId()).orElseThrow(DragonNotFoundException::new);
        dragonToUpdate.setName(dragonDto.getName());
        dragonToUpdate.setCoordinates(coordinates);
        dragonToUpdate.setAge(dragonDto.getAge());
        dragonToUpdate.setSpeaking(dragonDto.getSpeaking());
        dragonToUpdate.setCave(dragonCave);
        System.out.println("Check person");
        if (dragonDto.getPerson() != null) {
            System.out.println(personService.findPersonById(dragonDto.getPerson().getId()));
            dragonToUpdate.setPerson(personService.findPersonById(dragonDto.getPerson().getId()));
        }
        dragonToUpdate.setCharacter(dragonDto.getDragonCharacter());
        dragonToUpdate.setHead(dragonHead);
        dragonToUpdate.setDescription(dragonDto.getDescription());
        return mapper.toDragonDto(dragonRepository.save(dragonToUpdate));

    }

    @Retryable(value = { CannotAcquireLockException.class },
            backoff = @Backoff(delay = 1000, multiplier = 2))
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.SERIALIZABLE)
    public UploadHistory fileUpload(MultipartFile file) {
        UploadHistory result;
        try {
            UploadHistory uploadHistory = uploadHistoryService.createEmptyUpload();
            if (file.isEmpty() || file.getOriginalFilename() == null) {
                throw new FileUploadException("File is empty");
            }

            String objectUrl;
            List<DragonDtoRequest> dragonDtoList = objectMapper.readValue(file.getInputStream(), new TypeReference<>() {
            });
            for (DragonDtoRequest dragonDto : dragonDtoList) {
                Set<ConstraintViolation<DragonDtoRequest>> constraintViolations = validator.validate(dragonDto);
                if (!constraintViolations.isEmpty()) {
                    for (ConstraintViolation<DragonDtoRequest> constraintViolation : constraintViolations) {
                        System.out.println(constraintViolation.getMessage());
                    }
                    throw new ValidationException("Validation failed");
                }
                dragonDto.setOwner(securityUtil.getUserFromContext());
                dragonRepository.save(mapper.toDragon(dragonDto));
            }


            try {
                objectUrl = objectStorageService.uploadFile(file, uploadHistory.getId());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("123");
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "File upload failed");
            }

            uploadHistory.setObjectUrl(objectUrl);
            uploadHistory.setIsSuccess(Boolean.TRUE);
            uploadHistoryService.addNewUpload(uploadHistory);
            result = uploadHistory;
        } catch (ConnectException e) {
            System.out.println(1);
            uploadHistoryService.addBadUpload(UploadHistory.builder()
                    .objectUrl("")
                    .user(securityUtil.getUserFromContext())
                    .isSuccess(Boolean.FALSE)
                    .build());
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Object storage is not available");
        } catch (Exception e) {
            Throwable rootCause = com.google.common.base.Throwables.getRootCause(e);
            if (rootCause instanceof SQLException) {
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Database is not available");
            }

            uploadHistoryService.addBadUpload(
                    UploadHistory.builder()
                            .objectUrl("")
                            .user(securityUtil.getUserFromContext())
                            .isSuccess(Boolean.FALSE)
                            .build());
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Failed to upload file: " + e.getMessage());
        }
        return result;
    }


    public Map<String, Integer> groupDragonsByKiller() {
        List<Dragon> dragons = dragonRepository.findAll();
        Map<String, Integer> dragonsByKiller = new HashMap<>();
        for (Dragon dragon : dragons) {
            if (dragon.getPerson() == null) continue;
            if (!dragonsByKiller.containsKey(dragon.getPerson().getName())) {
                dragonsByKiller.put(dragon.getPerson().getName(), 1);
            } else {
                dragonsByKiller.put(dragon.getPerson().getName(), dragonsByKiller.get(dragon.getPerson().getName()) + 1);
            }
        }
        return dragonsByKiller;

    }


    public List<DragonDto> filterByTooth(Integer toothCount) {
        return dragonRepository.findAll().stream().filter(dragon -> dragon.getHead().getToothCount() < toothCount).map(mapper::toDragonDto).toList();
    }


    public List<DragonDto> getBySpeaking(boolean speaking) {
        return dragonRepository.findAllBySpeaking(speaking).stream().map(mapper::toDragonDto).toList();
    }
}
