package com.nsac.badgecollectorserver.configs;

import java.util.Arrays;
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
        return _ -> {
            // Only initialize if no badges exist
            if (badgeRepository.count() == 0) {
                Badge[] badges = {
                    createBadge("Friendship Bracelet", "Grab a tray, string, and some beads and and make a bracelet for you or a friend! Bring the finished bracelet to Angela to redeem your badge.", false, "1_BRACELET.png", "04:E0:F4:60:3E:61:80"),
                    createBadge("Hook and Ring", "Challenge a friend to a game of hook and ring. The first to land the ring on the hook 3 times wins. After winning, head to Nick to redeem your badge.", false, "2_HOOK-AND-RING.png", "04:66:7E:62:3E:61:80"),
                    createBadge("Wordle", "Copy this link https://mywordle.strivemath.com/?word=aashc to complete our custom Wordle. Show Angela your completed puzzle to redeem this badge.", false, "3_WORDLE.png", "04:4A:74:61:3E:61:81"),
                    createBadge("Knot Tying", "Grab some rope and choose one of the three provided knot instructions to follow. Bring your finished knot to Angela to redeem your badge.", false, "4_KNOT.png", "04:E3:E6:60:3E:61:80"),
                    createBadge("Magic Trick", "Learn a magic trick and perform it for Nick and/or Angela. This badge can be done individually or in a group. Redeem your badge from Angela\r\n" + //
                                                "\r\n" + //
                                                "Note: Bicycle cards can be found at the camp supply area. Ask Angela if you need other specific supplies.", false, "5_MAGIC-TRICK.png", "04:CE:4E:64:3E:61:80"),
                    createBadge("Connections", "Copy this link https://custom-connections-game.vercel.app/I5xGZCML17ptFdlS6b2B to complete our custom Connections. Show Angela your completed puzzle to redeem this badge. ", false, "6_CONNECTIONS.png", "04:43:8A:60:3E:61:80"),
                    createBadge("Take a Photo", "We’d love to remember our night with y’all! Take a photo with Nick and/or Angela to redeem this badge.", false, "7_PHOTO.png", "04:C9:32:62:3E:61:80"),
                    createBadge("Take a Drink", "Let’s share a drink! Find Nick and/or Angela and have a sip, shot, or shotgun of your choice of drink (alc or non-alc!). Redeem your badge from Nick.", false, "8_DRINK.png", "04:F5:50:64:3E:61:80"),
                    createBadge("Crossword", "Copy this link https://crosswordlabs.com/view/camping-130 to complete our custom Crossword. Show Nick your completed puzzle to redeem this badge.", false, "9_CROSSWORD.png", "04:52:22:64:3E:61:80"),
                    createBadge("Fun Fact", "Tell Nick and/or Angela a nature or animal-related fun fact. Redeem your badge from Nick.", false, "10_FUN-FACT.png", "04:8A:9C:60:3E:61:81"),
                    createBadge("Secret Talent", "Show Angela a secret talent.", false, "11_TALENT.png", "04:BA:62:61:3E:61:80"),
                    createBadge("Musical Flip Cup", "(Group Game) Survive musical flip cup.", false, "12_MUSICAL-FLIP-CUP.png", "04:E3:DD:63:3E:61:80"),
                    createBadge("Team Maze", "(Group Game) Be the first team to escape the maze.", false, "13_TEAM-MAZE.png", "04:3D:89:61:3E:61:80"),
                    createBadge("Origami", "Retrieve a sheet or two of origami paper from the camp supply center. Make an origami model of your choice and bring it to Angela to redeem your badge.", false, "14_ORIGAMI.png", "04:B5:68:61:3E:61:80"),
                    createBadge("Sobriety Test", "Ask Nick to conduct a sobriety test to redeem this badge. (Only after 6pm)", false, "15_SOBRIETY-TEST.png", "04:20:21:64:3E:61:80"),
                    createBadge("Introduction", "Introduce yourself to someone that you don’t know! Bring yourselves to Angela to redeem this badge.", false, "16_INTRODUCTION.png", "04:80:C9:63:3E:61:80"),
                    createBadge("Spotify Jam", "Scan the QR code on the TV and add a song to the Spotify Jam. Redeem your badge from Angela.", false, "17_SPOTIFY-JAM.png", "04:F5:A3:63:3E:61:80"),
                    createBadge("Team Drawing", "(Group Game) Be the best collaborative artists of the bunch.", false, "18_TEAM-DRAWING.png", "04:14:55:63:3E:61:80"),
                    createBadge("Trivia", "(Group Game) Be in the top 3 of our quiz game.", false, "19_TRIVIA.png", "04:E0:15:61:3E:61:80"),
                    createBadge("Felt Pennant", "Locate the felt pennant supplies and instructions and create your own. Show finished product to Angela.", false, "20_FELT-PENNANT.png", "04:9D:87:60:3E:61:80"),
                    createBadge("Horse Racing", "(Group Game) Bet on the winning horse.", false, "21_HORSE-RACING.png", "04:67:32:61:3E:61:80"),
                    createBadge("Paper Plane", "(Group Game) Win the airplane challenge.", false, "22_PAPER-PLANE.png", "04:E5:81:63:3E:61:81"),
                    createBadge("Rubber Band", "(Group Game) Win the rubber band game.", false, "23_RUBBER-BAND.png", "04:4B:3C:62:3E:61:80"),
                    createBadge("Marshmallow", "(Group Game) Win the marshmallow challenge.", false, "24_MARSHMALLOW.png", "04:8E:6C:62:3E:61:80"),
                    createBadge("Mushroom Pals", "Find Nick’s mushroom pals around the camp grounds. You will find all four hidden in plain sight – no need to open or move things around! Relay their locations to Nick to redeem this badge.", false, "25_MUSHROOM-PALS.png", "04:09:7F:62:3E:61:80"),
                    createBadge("Castle Cup", "(Group Game) Survive the Castle Cup", false, "26_CASTLE-CUP.png", "04:67:E1:60:3E:61:80"),
                    createBadge("Balloon Knockout", "(Group Game) Win the Balloon Knockout", false, "27_BALLOON-KNOCKOUT.png", "04:77:01:61:3E:61:81"),
                    createBadge("Self Portrait", "Find the self portrait station and grab a marker on the side of the fridge by and draw yourself in your style. Sign your portrait and redeem your badge from Angela.", false, "28_SELF-PORTRAIT.png", "04:29:95:61:3E:61:80"),
                    createBadge("Carpool Camper", "Be a DD.", true, "CARPOOL-CAMPER.png", "04:E9:C9:6C:4E:61:80"),
                    createBadge("Dressed in Theme", "Be dressed in scout attire.", true, "DRESSED-IN-THEME.png", "04:63:2B:63:3E:61:80"),
                    createBadge("First to Arrive", "Get here first.", true, "FIRST-TO-ARRIVE.png", "04:F6:FC:63:3E:61:80"),
                    createBadge("High", "Was high.", true, "HIGH.png", "04:C2:5A:61:3E:61:80"),
                    createBadge("Longest Commute", "Took the longest journey to get here.", true, "LONGEST-COMMUTE.png", "04:B0:09:63:3E:61:80"),
                    createBadge("Matcha Bringers", "Brought us matcha", true, "MATCHA.png", "04:E4:90:60:3E:61:80"),
                    createBadge("Party Helper", "Helped us setup.", true, "PARTY-HELPER.png", "04:9E:1A:64:3E:61:80"),
                    createBadge("Sloshed", "Got sloshed.", true, "SLOSHED.png", "04:A2:1A:64:3E:61:80"),
                    createBadge("Website Tester", "Helped us test the website", true, "WEBSITE-TESTER.png", "04:4E:40:64:3E:61:80"),
                    createBadge("Wore Crocs", "Sexy", true, "WORE-CROCS.png", "04:1A:B4:60:3E:61:80"),
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