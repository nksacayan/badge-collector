package com.nsac.badgecollectorserver.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nsac.badgecollectorserver.entities.Badge;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Integer> {
    List<Badge> findByName(String name);
}
