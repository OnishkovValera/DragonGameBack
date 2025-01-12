package org.onishkoff.itmo.IS1.repository;

import org.onishkoff.itmo.IS1.model.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Page<Team> findAllByOwnerId(Long ownerId, Pageable pageable);
}
