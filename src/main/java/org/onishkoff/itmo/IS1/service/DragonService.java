package org.onishkoff.itmo.IS1.service;

import lombok.RequiredArgsConstructor;
import org.onishkoff.itmo.IS1.dto.model.request.DragonDtoRequest;
import org.onishkoff.itmo.IS1.dto.model.response.DragonDto;
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

    public DragonDto getDragon(int id){
        return mapper.toDragonDto(dragonRepository.findById(id).orElseThrow());
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
}
