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
                    createBadge("Explorer", "Unlocked for visiting 50 unique locations.", false, "BRACELET.png"),
                    createBadge("Speed Runner", "Earned for completing a challenge in record time.", false, "CONNECTIONS.png"),
                    createBadge("Master Crafter", "Awarded for crafting 500 items.", false, "CROSSWORD.png"),
                    createBadge("Social Butterfly", "Given for interacting with 100 different players.", false, "DRINK.png"),
                    createBadge("Strategist", "Earned for winning 10 matches using tactical skills.", false, "Badge_Placeholder.png"),
                    createBadge("Loyal Member", "Awarded for logging in every day for a month.", false, "Badge_Placeholder.png"),
                    createBadge("Puzzle Solver", "Unlocked for completing all logic puzzles.", false, "Badge_Placeholder.png"),
                    createBadge("Sharpshooter", "Earned for achieving 90% accuracy in a shooting challenge.", false, "Badge_Placeholder.png"),
                    createBadge("Treasure Hunter", "Awarded for finding all hidden collectibles.", false, "Badge_Placeholder.png"),
                    createBadge("Beast Tamer", "Unlocked for taming 10 rare creatures.", false, "Badge_Placeholder.png"),
                    createBadge("Architect", "Earned for building a structure with 1000 blocks.", false, "Badge_Placeholder.png"),
                    createBadge("Historian", "Awarded for unlocking all lore entries.", false, "Badge_Placeholder.png"),
                    createBadge("Gladiator", "Unlocked for winning 50 arena battles.", false, "Badge_Placeholder.png"),
                    createBadge("Altruist", "Earned for helping 20 other players complete quests.", false, "Badge_Placeholder.png"),
                    createBadge("Collector Supreme", "Awarded for collecting every item in the game.", false, "Badge_Placeholder.png"),
                    createBadge("Marathoner", "Unlocked for traveling 100 miles in-game.", false, "Badge_Placeholder.png"),
                    createBadge("Silent Assassin", "Earned for completing a stealth mission undetected.", true, "Badge_Placeholder.png"),
                    createBadge("Event Champion", "Awarded for winning a seasonal event.", true, "Badge_Placeholder.png"),
                    createBadge("Bug Squisher", "Unlocked for reporting 10 verified bugs.", true, "Badge_Placeholder.png"),
                    createBadge("Customizer", "Earned for creating 10 unique character outfits.", true, "Badge_Placeholder.png")
                };
                
                badgeRepository.saveAll(Arrays.asList(badges));
            }

            List<Badge> retrievedBadges = badgeRepository.findAll();
            Set<Badge> nicksBadges = new HashSet<>();
            nicksBadges.add(retrievedBadges.get(0));
            nicksBadges.add(retrievedBadges.get(1));
            nicksBadges.add(retrievedBadges.get(2));
            nicksBadges.add(retrievedBadges.get(19));
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

    private Badge createBadge(String name, String description, boolean isSecret, String imageFilename) {
        Badge badge = new Badge();
        badge.setName(name);
        badge.setDescription(description);
        if (imageFilename != null && !imageFilename.isBlank()) {
            badge.setImageFilename(imageFilename);
        }
        else {
            // Derive image filename from name (lowercase, spaces -> hyphens, remove non-alphanumeric/hyphen)
            String sanitized = name.toLowerCase().replaceAll("\\s+", "-").replaceAll("[^a-z0-9\\-]", "");
            badge.setImageFilename(sanitized + ".png");
        }
        badge.setSecret(isSecret);
        return badge;
    }
}