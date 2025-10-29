package com.nsac.badgecollectorserver.configs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nsac.badgecollectorserver.entities.Badge;
import com.nsac.badgecollectorserver.entities.User;
import com.nsac.badgecollectorserver.repositories.BadgeRepository;
import com.nsac.badgecollectorserver.repositories.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(BadgeRepository badgeRepository, UserRepository userRepository) {
        return _ -> {
            // Only initialize if no badges exist
            if (badgeRepository.count() == 0) {
                Badge[] badges = {
                    createBadge("Explorer", "Unlocked for visiting 50 unique locations.", false),
                    createBadge("Speed Runner", "Earned for completing a challenge in record time.", false),
                    createBadge("Master Crafter", "Awarded for crafting 500 items.", false),
                    createBadge("Social Butterfly", "Given for interacting with 100 different players.", false),
                    createBadge("Strategist", "Earned for winning 10 matches using tactical skills.", false),
                    createBadge("Loyal Member", "Awarded for logging in every day for a month.", false),
                    createBadge("Puzzle Solver", "Unlocked for completing all logic puzzles.", false),
                    createBadge("Sharpshooter", "Earned for achieving 90% accuracy in a shooting challenge.", false),
                    createBadge("Treasure Hunter", "Awarded for finding all hidden collectibles.", false),
                    createBadge("Beast Tamer", "Unlocked for taming 10 rare creatures.", false),
                    createBadge("Architect", "Earned for building a structure with 1000 blocks.", false),
                    createBadge("Historian", "Awarded for unlocking all lore entries.", false),
                    createBadge("Gladiator", "Unlocked for winning 50 arena battles.", false),
                    createBadge("Altruist", "Earned for helping 20 other players complete quests.", false),
                    createBadge("Collector Supreme", "Awarded for collecting every item in the game.", false),
                    createBadge("Marathoner", "Unlocked for traveling 100 miles in-game.", false),
                    createBadge("Silent Assassin", "Earned for completing a stealth mission undetected.", true),
                    createBadge("Event Champion", "Awarded for winning a seasonal event.", true),
                    createBadge("Bug Squisher", "Unlocked for reporting 10 verified bugs.", true),
                    createBadge("Customizer", "Earned for creating 10 unique character outfits.", true)
                };
                
                badgeRepository.saveAll(Arrays.asList(badges));
            }

            List<Badge> retrievedBadges = badgeRepository.findAll();
            Set<Badge> nicksBadges = new HashSet<>();
            nicksBadges.add(retrievedBadges.get(0));
            nicksBadges.add(retrievedBadges.get(1));
            nicksBadges.add(retrievedBadges.get(2));
            Set<Badge> angelasBadges = new HashSet<>();
            angelasBadges.add(retrievedBadges.get(2));
            angelasBadges.add(retrievedBadges.get(3));
            angelasBadges.add(retrievedBadges.get(4));

            if (userRepository.count() == 0) {
                List<User> users = new ArrayList<>();
                users.add(User.builder()
                    .name("Test Nick")
                    .badges(new HashSet<>(nicksBadges))
                    .build()
                );
                users.add(User.builder()
                    .name("Test Angela")
                    .badges(new HashSet<>(angelasBadges))
                    .build()
                );
                userRepository.saveAll(users);
            }
        };
    }

    private Badge createBadge(String name, String description, boolean isSecret) {
        Badge badge = new Badge();
        badge.setName(name);
        badge.setDescription(description);
        // Derive image filename from name (lowercase, spaces -> hyphens, remove non-alphanumeric/hyphen)
        String sanitized = name.toLowerCase().replaceAll("\\s+", "-").replaceAll("[^a-z0-9\\-]", "");
        badge.setImageFilename(sanitized + ".png");
        badge.setSecret(isSecret);
        return badge;
    }
}