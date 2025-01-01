package org.onishkoff.itmo.IS1.repository;


import org.onishkoff.itmo.IS1.model.DragonCave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DragonCaveRepository extends JpaRepository<DragonCave, Long> {
    Optional<DragonCave> findById(Long id);
}
