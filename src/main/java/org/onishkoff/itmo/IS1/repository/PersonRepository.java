package org.onishkoff.itmo.IS1.repository;

import org.onishkoff.itmo.IS1.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonRepository extends JpaRepository<Person, Long> {
    Page<Person> findAllByOwnerId(Long ownerId, Pageable pageable);

}
