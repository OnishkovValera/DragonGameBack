package org.onishkoff.itmo.IS1.repository;

import org.onishkoff.itmo.IS1.model.Coordinates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoordinateRepository extends JpaRepository<Coordinates, Long> {
    Optional<Coordinates> findById(Long id);
}
