package org.onishkoff.itmo.IS1.service;

import lombok.RequiredArgsConstructor;
import org.onishkoff.itmo.IS1.dto.model.request.DragonDtoRequest;
import org.onishkoff.itmo.IS1.dto.model.response.DragonDto;
import org.onishkoff.itmo.IS1.model.*;
import org.onishkoff.itmo.IS1.repository.DragonRepository;
import org.onishkoff.itmo.IS1.util.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DragonService{

    private final PersonService personService;
    private final CoordinateService coordinateService;
    private final DragonHeadService dragonHeadService;
    private final DragonCaveService dragonCaveService;
    private final DragonRepository dragonRepository;
    private final Mapper mapper;

    public DragonDto getDragon(int id){
        return mapper.toDragonDto(dragonRepository.findById(id).orElseThrow());
    }

    public Page<DragonDto> getDragons(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page, size);
        return dragonRepository.findAll(pageable).map(mapper::toDragonDto);
    }


    public DragonDto createDragon(DragonDtoRequest dto) {
        DragonCave cave = null;
        DragonHead head = null;
        Coordinates coordinates = null;
        Person person = null;

        if(dto.getCaveId() != null){
            cave = dragonCaveService.findDragonCaveById(dto.getCaveId());
        }
        if(dto.getHeadId() != null){
            head = dragonHeadService.findById(dto.getHeadId());
        }
        if(dto.getCoordinatesId() != null){
            coordinates = coordinateService.findCoordinatesById(dto.getCoordinatesId());
        }
        if(dto.getPersonId() != null){
            person = personService.findPersonById(dto.getPersonId());
        }
        Dragon dragon = dragonRepository.save(mapper.toDragon(dto, coordinates, cave, person, head));
        return mapper.toDragonDto(dragon);
    }
}
