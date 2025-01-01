package org.onishkoff.itmo.IS1.service;

import lombok.RequiredArgsConstructor;
import org.onishkoff.itmo.IS1.exception.DragonHeadNotFoundException;
import org.onishkoff.itmo.IS1.model.DragonHead;
import org.onishkoff.itmo.IS1.repository.DragonHeadRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DragonHeadService {

    private final DragonHeadRepository dragonHeadRepository;

    public DragonHead findById(Long id) {
        return dragonHeadRepository.findById(id).orElseThrow(DragonHeadNotFoundException::new);
    }


}
