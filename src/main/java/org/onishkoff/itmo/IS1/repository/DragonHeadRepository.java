package org.onishkoff.itmo.IS1.repository;

import org.onishkoff.itmo.IS1.model.DragonHead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DragonHeadRepository extends JpaRepository<DragonHead, Long> {
    Optional<DragonHead> findById(Long id);
}
