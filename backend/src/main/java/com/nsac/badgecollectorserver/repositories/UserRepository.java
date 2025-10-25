package com.nsac.badgecollectorserver.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nsac.badgecollectorserver.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Find by name
    Optional<User> findByName(String name);
}
