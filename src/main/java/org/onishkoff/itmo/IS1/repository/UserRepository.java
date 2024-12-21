package org.onishkoff.itmo.IS1.repository;

import org.onishkoff.itmo.IS1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);

}
