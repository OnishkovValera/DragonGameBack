package org.onishkoff.itmo.IS1.repository;

import org.onishkoff.itmo.IS1.model.AdminRequests;
import org.onishkoff.itmo.IS1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<AdminRequests, Long> {
    Optional<AdminRequests> findByUser(User user);

}
