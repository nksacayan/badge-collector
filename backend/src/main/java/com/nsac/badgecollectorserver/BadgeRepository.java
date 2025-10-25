package com.nsac.badgecollectorserver;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Integer> {
    // JpaRepository already provides findAll() and findById()
    
    // Custom method for finding by name
    List<Badge> findByName(String name);
}
