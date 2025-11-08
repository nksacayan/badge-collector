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
                    createBadge("Friendship Bracelet", "Grab a tray, string, and some beads and and make a bracelet for you or a friend! Bring the finished bracelet to Angela to redeem your badge.", false, "1_BRACELET.png", "04:CA:8A:62:3E:61:81"),
                    createBadge("Hook and Ring", "Challenge a friend to a game of hook and ring. The first to land the ring on the hook 3 times wins. After winning, head to Nick to redeem your badge.", false, "2_HOOK-AND-RING.png", "04:21:21:63:3E:61:80"),
                    createBadge("Wordle", "Copy this link to complete our custom Wordle. Show Angela your completed puzzle to redeem this badge.", false, "3_WORDLE.png", "04:80:C9:63:3E:61:80"),
                    createBadge("Knot Tying", "Grab some rope and choose one of the three provided knot instructions to follow. Bring your finished knot to Angela to redeem your badge.", false, "4_KNOT.png", "04:4E:40:64:3E:61:80"),
                    createBadge("Magic Trick", "Learn a magic trick and perform it for Nick and/or Angela. This badge can be done individually or in a group. Redeem your badge from Angela\r\n" + //
                                                "\r\n" + //
                                                "Note: Bicycle cards can be found at the camp supply area. Ask Angela if you need other specific supplies.", false, "5_MAGIC-TRICK.png"),
                    createBadge("Connections", "Copy this link to complete our custom Connections. Show Angela your completed puzzle to redeem this badge. ", false, "6_CONNECTIONS.png"),
                    createBadge("Take a Photo", "We’d love to remember our night with y’all! Take a photo with Nick and/or Angela to redeem this badge.", false, "7_PHOTO.png"),
                    createBadge("Take a Drink", "Let’s share a drink! Find Nick and/or Angela and have a sip, shot, or shotgun of your choice of drink (alc or non-alc!). Redeem your badge from Nick.", false, "8_DRINK.png"),
                    createBadge("Crossword", "Copy this link to complete our custom Crossword. Show Nick your completed puzzle to redeem this badge.", false, "9_CROSSWORD.png"),
                    createBadge("Fun Fact", "Tell Nick and/or Angela a nature or animal-related fun fact. Redeem your badge from Nick.", false, "10_FUN-FACT.png"),
                    createBadge("Secret Talent", "Show Angela a secret talent.", false, "11_TALENT.png"),
                    createBadge("Musical Flip Cup", "(Group Game) Survive musical flip cup.", false, "12_MUSICAL-FLIP-CUP.png"),
                    createBadge("Team Maze", "(Group Game) Be the first team to escape the maze.", false, "13_TEAM-MAZE.png"),
                    createBadge("Origami", "Retrieve a sheet or two of origami paper from the camp supply center. Make an origami model of your choice and bring it to Angela to redeem your badge.", false, "14_ORIGAMI.png"),
                    createBadge("Sobriety Test", "Ask Nick to conduct a sobriety test to redeem this badge. (Only after 6pm)", false, "15_SOBRIETY-TEST.png"),
                    createBadge("Introduction", "Introduce yourself to someone that you don’t know! Bring yourselves to Angela to redeem this badge.", false, "16_INTRODUCTION.png"),
                    createBadge("Spotify Jam", "Scan the QR code on the TV and add a song to the Spotify Jam. Redeem your badge from Angela.", false, "17_SPOTIFY-JAM.png"),
                    createBadge("Team Drawing", "(Group Game) Be the best collaborative artists of the bunch.", false, "18_TEAM-DRAWING.png"),
                    createBadge("Trivia", "(Group Game) Be in the top 3 of our quiz game.", false, "19_TRIVIA.png"),
                    createBadge("Felt Pennant", "Locate the felt pennant supplies and instructions and create your own. Show finished product to Angela.", false, "20_FELT-PENNANT.png"),
                    createBadge("Horse Racing", "(Group Game) Bet on the winning horse.", false, "21_HORSE-RACING.png"),
                    createBadge("Paper Plane", "(Group Game) Win the airplane challenge.", false, "22_PAPER-PLANE.png"),
                    createBadge("Rubber Band", "(Group Game) Win the rubber band game.", false, "23_RUBBER-BAND.png"),
                    createBadge("Marshmallow", "(Group Game) Win the marshmallow challenge.", false, "24_MARSHMALLOW.png"),
                    createBadge("Mushroom Pals", "Find Nick’s mushroom pals around the camp grounds. You will find all four hidden in plain sight – no need to open or move things around! Relay their locations to Nick to redeem this badge.", false, "25_MUSHROOM-PALS.png"),
                    createBadge("Castle Cup", "(Group Game) Survive the Castle Cup", false, "26_CASTLE-CUP.png"),
                    createBadge("Balloon Knockout", "(Group Game) Win the Balloon Knockout", false, "27_BALLOON-KNOCKOUT.png"),
                    createBadge("Self Portrait", "Find the self portrait station and grab a marker on the side of the fridge by and draw yourself in your style. Sign your portrait and redeem your badge from Angela.", false, "28_SELF-PORTRAIT.png"),
                    createBadge("Carpool Camper", "Be a DD.", true, "CARPOOL-CAMPER.png"),
                    createBadge("Dressed in Theme", "Be dressed in scout attire.", true, "DRESSED-IN-THEME.png"),
                    createBadge("First to Arrive", "Get here first.", true, "FIRST-TO-ARRIVE.png"),
                    createBadge("High", "Was high.", true, "HIGH.png"),
                    createBadge("Longest Commute", "Took the longest journey to get here.", true, "LONGEST-COMMUTE.png"),
                    createBadge("Matcha Bringers", "Brought us matcha", true, "MATCHA.png"),
                    createBadge("Party Helper", "Helped us setup.", true, "PARTY-HELPER.png"),
                    createBadge("Sloshed", "Got sloshed.", true, "SLOSHED.png"),
                    createBadge("Website Tester", "Helped us test the website", true, "WEBSITE-TESTER.png"),
                    createBadge("Wore Crocs", "Sexy", true, "WORE-CROCS.png"),
                };
                
                badgeRepository.saveAll(Arrays.asList(badges));
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