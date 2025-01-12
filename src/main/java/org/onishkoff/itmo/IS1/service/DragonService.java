package org.onishkoff.itmo.IS1.service;

import lombok.RequiredArgsConstructor;
import org.onishkoff.itmo.IS1.dto.model.request.DragonDtoRequest;
import org.onishkoff.itmo.IS1.dto.model.response.DragonDto;
import org.onishkoff.itmo.IS1.exception.DragonNotFoundException;
import org.onishkoff.itmo.IS1.model.*;
import org.onishkoff.itmo.IS1.repository.DragonRepository;
import org.onishkoff.itmo.IS1.util.Mapper;
import org.onishkoff.itmo.IS1.util.SecurityUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DragonService{

    private final DragonRepository dragonRepository;
    private final Mapper mapper;
    private final SecurityUtil securityUtil;
    private final PersonService personService;
    private final CoordinateService coordinateService;
    private final DragonCaveService dragonCaveService;
    private final DragonHeadService dragonHeadService;

    public DragonDto getDragonDto(int id){
        return mapper.toDragonDto(dragonRepository.findById(id).orElseThrow());
    }

    public Dragon getDragon(Integer id){
        return dragonRepository.findById(id).orElseThrow(DragonNotFoundException::new);
    }


    public Page<DragonDto> getDragons(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Dragon> rawPages = dragonRepository.findAllByOwnerId(securityUtil.getUserFromContext().getId(), pageable);
        return rawPages.map(mapper::toDragonDto);
    }

    @Transactional
    public DragonDto createDragon(DragonDtoRequest dto) {
        dto.setOwner(securityUtil.getUserFromContext());
        if(dto.getDragonHead() != null && dto.getDragonHead().getToothCount() != null){
            dto.getDragonHead().setId(null);
        }
        Dragon dragon = dragonRepository.save(mapper.toDragon(dto));

        return mapper.toDragonDto(dragon);
    }

    public void delete(Integer id) {
        dragonRepository.deleteById(id);
    }

    public DragonDto update(DragonDtoRequest dragonDto) {

        Coordinates coordinates = coordinateService.findCoordinatesById(dragonDto.getCoordinates().getId());
        coordinates.setX(dragonDto.getCoordinates().getX());
        coordinates.setY(dragonDto.getCoordinates().getY());

        DragonCave dragonCave = dragonCaveService.findDragonCaveById(dragonDto.getDragonCave().getId());
        dragonCave.setDepth(dragonDto.getDragonCave().getDepth());
        dragonCave.setNumberOfTreasures(dragonDto.getDragonCave().getNumberOfTreasures());

        DragonHead dragonHead = dragonHeadService.findById(dragonDto.getDragonHead().getId());
        dragonHead.setToothCount(dragonDto.getDragonHead().getToothCount());

        Dragon dragonToUpdate = dragonRepository.findById(dragonDto.getId()).orElseThrow(DragonNotFoundException::new);
        dragonToUpdate.setName(dragonDto.getName());
        dragonToUpdate.setCoordinates(coordinates);
        dragonToUpdate.setAge(dragonDto.getAge());
        dragonToUpdate.setSpeaking(dragonDto.getSpeaking());
        dragonToUpdate.setCave(dragonCave);
        if (dragonDto.getPersonId() != null) {
            dragonToUpdate.setPerson(personService.findPersonById(dragonDto.getPersonId()));
        }
        dragonToUpdate.setCharacter(dragonDto.getDragonCharacter());
        dragonToUpdate.setHead(dragonHead);
        dragonToUpdate.setDescription(dragonDto.getDescription());
        return mapper.toDragonDto(dragonRepository.save(mapper.toDragon(dragonDto)));

    }
}
