package org.onishkoff.itmo.IS1.repository;

import org.onishkoff.itmo.IS1.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
