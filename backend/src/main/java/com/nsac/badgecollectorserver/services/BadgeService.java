package com.nsac.badgecollectorserver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nsac.badgecollectorserver.models.BadgeDTO;
import com.nsac.badgecollectorserver.repositories.BadgeRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BadgeService {
    private final BadgeRepository badgeRepository;


    public List<BadgeDTO> findBadgesByName(String name) {
        return badgeRepository.findByName(name).stream()
            .map(BadgeDTO::fromEntity)
            .collect(Collectors.toList());
    }

    public List<BadgeDTO> getAllBadges() {
        return badgeRepository.findAll().stream()
            .map(BadgeDTO::fromEntity)
            .collect(Collectors.toList());
    }

    public BadgeDTO getBadgeById(Integer id) {
        return badgeRepository.findById(id)
            .map(BadgeDTO::fromEntity)
            .orElseThrow(() -> new RuntimeException("Badge not found with id: " + id));
    }

    public void deleteBadge(Integer id) {
        badgeRepository.deleteById(id);
    }
}
