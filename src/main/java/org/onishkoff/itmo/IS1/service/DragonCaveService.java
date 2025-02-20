package org.onishkoff.itmo.IS1.service;


import lombok.RequiredArgsConstructor;
import org.onishkoff.itmo.IS1.exception.DragonCaveNotFoundException;
import org.onishkoff.itmo.IS1.model.DragonCave;
import org.onishkoff.itmo.IS1.repository.DragonCaveRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DragonCaveService {


    private final DragonCaveRepository dragonCaveRepository;

    public DragonCave findDragonCaveById(Long id) {
        return dragonCaveRepository.findById(id).orElseThrow(DragonCaveNotFoundException::new);
    }

    public DragonCave saveNewCave(Double depth, Double numberOfTreasures) {
        return dragonCaveRepository.save(DragonCave.builder()
                .numberOfTreasures(numberOfTreasures)
                .depth(depth)
                .build());
    }

    public DragonCave createNewCave(Double depth, Double numberOfTreasures) {
        return DragonCave.builder()
                .numberOfTreasures(numberOfTreasures)
                .depth(depth)
                .build();
    }
}
