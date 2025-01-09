package org.onishkoff.itmo.IS1.repository;

import org.onishkoff.itmo.IS1.model.Dragon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DragonRepository extends JpaRepository<Dragon, Integer> {
    Page<Dragon> findAllByOwnerId(Long ownerId, Pageable pageable);
}
