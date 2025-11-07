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
                    createBadge("Test Badge 1", "Test Description", false, "1-SECRET.png", "04:CA:8A:62:3E:61:81"),
                    createBadge("Test Badge 2", "Test Description", false, "2-SECRET.png", "04:21:21:63:3E:61:80"),
                    createBadge("Test Badge 3", "Test Description", false, "3-SECRET.png", "04:80:C9:63:3E:61:80"),
                    createBadge("Test Badge 4", "Test Description", true, "WEBSITE-TESTER.png", "04:4E:40:64:3E:61:80"),
                    createBadge("Test Badge 5", "Test Description", false, "5-SECRET.png"),
                    createBadge("Test Badge 6", "Test Description", false, "5-SECRET.png"),
                    createBadge("Test Badge 7", "Test Description", false, "5-SECRET.png"),
                    createBadge("Test Badge 8", "Test Description", false, "5-SECRET.png"),
                    createBadge("Test Badge 9", "Test Description", false, "5-SECRET.png"),
                    createBadge("Test Badge 10", "Test Description", false, "5-SECRET.png"),
                    createBadge("Test Badge 11", "Test Description", false, "5-SECRET.png"),
                    createBadge("Test Badge 12", "Test Description", false, "5-SECRET.png"),
                    createBadge("Test Badge 13", "Test Description", false, "5-SECRET.png"),
                    createBadge("Test Badge 14", "Test Description", false, "5-SECRET.png"),
                    createBadge("Test Badge 15", "Test Description", false, "5-SECRET.png"),
                    createBadge("Test Badge 16", "Test Description", false, "5-SECRET.png"),
                    createBadge("Test Badge 17", "Test Description", false, "5-SECRET.png"),
                    createBadge("Test Badge 18", "Test Description", false, "5-SECRET.png"),
                    createBadge("Test Badge 19", "Test Description", false, "5-SECRET.png"),
                    createBadge("Test Badge 20", "Test Description", false, "5-SECRET.png"),
                };
                
                badgeRepository.saveAll(Arrays.asList(badges));
            }

            List<Badge> retrievedBadges = badgeRepository.findAll();
            Set<Badge> nicksBadges = new HashSet<>();
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


    private String checkBadgeImageFilename(String imageFilename) {
        if (imageFilename != null && !imageFilename.isBlank()) {
            return imageFilename;
        }
        else {
            // Derive image filename from name (lowercase, spaces -> hyphens, remove non-alphanumeric/hyphen)
            String sanitized = imageFilename.toLowerCase().replaceAll("\\s+", "-").replaceAll("[^a-z0-9\\-]", "");
            return sanitized + ".png";
        }
    }

    private Badge createBadge(String name, String description, boolean isSecret, String imageFilename) {
        Badge badge = new Badge();
        badge.setName(name);
        badge.setDescription(description);
        badge.setNfcTagId(null);
        badge.setImageFilename(checkBadgeImageFilename(imageFilename));
        badge.setSecret(isSecret);
        return badge;
    }

    private Badge createBadge(String name, String description, boolean isSecret, String imageFilename, String nfcTagId) {
        Badge badge = new Badge();
        badge.setName(name);
        badge.setDescription(description);
        badge.setNfcTagId(nfcTagId);
        badge.setImageFilename(checkBadgeImageFilename(imageFilename));
        badge.setSecret(isSecret);
        return badge;
    }
}