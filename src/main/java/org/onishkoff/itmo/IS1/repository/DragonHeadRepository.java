package org.onishkoff.itmo.IS1.repository;

import org.onishkoff.itmo.IS1.model.DragonHead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DragonHeadRepository extends JpaRepository<DragonHead, Long> {
    Optional<DragonHead> findById(Long id);
}
