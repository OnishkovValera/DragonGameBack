package org.onishkoff.itmo.IS1.repository;

import org.onishkoff.itmo.IS1.model.Dragon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DragonRepository extends JpaRepository<Dragon, Integer>, JpaSpecificationExecutor<Dragon> {
    Page<Dragon> findAllByOwnerId(Long ownerId, Pageable pageable);
    List<Dragon> findAllByOwnerId(Long ownerId);
    List<Dragon> findAllBySpeaking(Boolean speaking);
}
