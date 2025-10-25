package com.nsac.badgecollectorserver.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nsac.badgecollectorserver.entities.Badge;
import com.nsac.badgecollectorserver.repositories.BadgeRepository;
import com.nsac.badgecollectorserver.repositories.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(BadgeRepository badgeRepository, UserRepository userRepository) {
        return args -> {
            // Only initialize if no badges exist
            if (badgeRepository.count() == 0) {
                Badge[] badges = {
                    createBadge("Explorer", "Unlocked for visiting 50 unique locations."),
                    createBadge("Speed Runner", "Earned for completing a challenge in record time."),
                    createBadge("Master Crafter", "Awarded for crafting 500 items."),
                    createBadge("Social Butterfly", "Given for interacting with 100 different players."),
                    createBadge("Strategist", "Earned for winning 10 matches using tactical skills."),
                    createBadge("Loyal Member", "Awarded for logging in every day for a month."),
                    createBadge("Puzzle Solver", "Unlocked for completing all logic puzzles."),
                    createBadge("Sharpshooter", "Earned for achieving 90% accuracy in a shooting challenge."),
                    createBadge("Treasure Hunter", "Awarded for finding all hidden collectibles."),
                    createBadge("Beast Tamer", "Unlocked for taming 10 rare creatures."),
                    createBadge("Architect", "Earned for building a structure with 1000 blocks."),
                    createBadge("Historian", "Awarded for unlocking all lore entries."),
                    createBadge("Gladiator", "Unlocked for winning 50 arena battles."),
                    createBadge("Altruist", "Earned for helping 20 other players complete quests."),
                    createBadge("Collector Supreme", "Awarded for collecting every item in the game."),
                    createBadge("Marathoner", "Unlocked for traveling 100 miles in-game."),
                    createBadge("Silent Assassin", "Earned for completing a stealth mission undetected."),
                    createBadge("Event Champion", "Awarded for winning a seasonal event."),
                    createBadge("Bug Squisher", "Unlocked for reporting 10 verified bugs."),
                    createBadge("Customizer", "Earned for creating 10 unique character outfits.")
                };
                
                for (Badge badge : badges) {
                    badgeRepository.save(badge);
                }
            }
        };
    }

    private Badge createBadge(String name, String description) {
        Badge badge = new Badge();
        badge.setName(name);
        badge.setDescription(description);
        return badge;
    }
}