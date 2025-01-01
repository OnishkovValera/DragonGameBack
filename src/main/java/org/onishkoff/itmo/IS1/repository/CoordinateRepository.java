package org.onishkoff.itmo.IS1.repository;

import org.onishkoff.itmo.IS1.model.Coordinates;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoordinateRepository extends JpaRepository<Coordinates, Long> {
    Optional<Coordinates> findById(Long id);
}
