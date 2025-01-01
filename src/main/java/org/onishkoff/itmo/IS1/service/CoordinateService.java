package org.onishkoff.itmo.IS1.service;

import lombok.RequiredArgsConstructor;
import org.onishkoff.itmo.IS1.exception.DragonCaveNotFoundException;
import org.onishkoff.itmo.IS1.model.Coordinates;
import org.onishkoff.itmo.IS1.repository.CoordinateRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CoordinateService {

    private final CoordinateRepository coordinateRepository;

    public Coordinates findCoordinatesById(Long id) {
        return coordinateRepository.findById(id).orElseThrow(DragonCaveNotFoundException::new);
    }

}
