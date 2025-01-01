package org.onishkoff.itmo.IS1.repository;

import org.onishkoff.itmo.IS1.model.Dragon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DragonRepository extends JpaRepository<Dragon, Integer> {
    Optional<Dragon> findById(Integer id);


}
