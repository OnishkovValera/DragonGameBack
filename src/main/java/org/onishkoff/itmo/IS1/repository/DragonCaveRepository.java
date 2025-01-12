package org.onishkoff.itmo.IS1.repository;


import org.onishkoff.itmo.IS1.model.DragonCave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DragonCaveRepository extends JpaRepository<DragonCave, Long> {
    Optional<DragonCave> findById(Long id);
}
