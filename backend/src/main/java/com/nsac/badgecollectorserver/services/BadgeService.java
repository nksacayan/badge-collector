package com.nsac.badgecollectorserver.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nsac.badgecollectorserver.entities.Badge;
import com.nsac.badgecollectorserver.repositories.BadgeRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BadgeService {
    private final BadgeRepository badgeRepository;

    public Badge createBadge(Badge badge) {
        return badgeRepository.save(badge);
    }

    public List<Badge> findBadgesByName(String name) {
        return badgeRepository.findByName(name);
    }

    public List<Badge> getAllBadges() {
        return badgeRepository.findAll();
    }

    public Badge getBadgeById(Integer id) {
        return badgeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Badge not found with id: " + id));
    }

    public void deleteBadge(Integer id) {
        badgeRepository.deleteById(id);
    }
}
