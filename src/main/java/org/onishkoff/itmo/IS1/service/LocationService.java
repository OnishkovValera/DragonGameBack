package org.onishkoff.itmo.IS1.service;

import lombok.RequiredArgsConstructor;
import org.onishkoff.itmo.IS1.exception.LocationNotFoundException;
import org.onishkoff.itmo.IS1.model.Location;
import org.onishkoff.itmo.IS1.repository.LocationRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    public Location getLocation(Long id) {
        return locationRepository.findById(id).orElseThrow(LocationNotFoundException::new);
    }

}
