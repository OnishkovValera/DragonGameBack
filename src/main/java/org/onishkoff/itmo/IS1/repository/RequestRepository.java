package org.onishkoff.itmo.IS1.repository;

import org.onishkoff.itmo.IS1.model.AdminRequests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<AdminRequests, Long> {



}
