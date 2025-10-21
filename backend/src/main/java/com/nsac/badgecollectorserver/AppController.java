package com.nsac.badgecollectorserver;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {
    "http://localhost:5173",
    "http://127.0.0.1:5173"
})
public class AppController {

    private final UserRepository userRepository;
    private final BadgeRepository badgeRepository;

    public AppController(UserRepository userRepository, BadgeRepository badgeRepository) {
        this.userRepository = userRepository;
        this.badgeRepository = badgeRepository;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> users() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    // create user
    @GetMapping("/user/create")
    public ResponseEntity<User> createUser(@RequestParam String name) {
        User user = userRepository.createUser(name);
        return ResponseEntity.ok(user);
    }

    // get user
    @GetMapping("/user")
    public ResponseEntity<User> loginUser(@RequestParam String name) {
        User user = userRepository.getUser(name);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // add badge to user
    @GetMapping("/user/add-badge")
    public ResponseEntity<User> addBadgeToUser(@RequestParam int userId, @RequestParam int badgeId) {
        User user = userRepository.addBadgeToUser(userId, badgeId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // get all badges
    @GetMapping("/badges")
    public ResponseEntity<List<Badge>> getAllBadges() {
        List<Badge> badges = badgeRepository.findAll();
        return ResponseEntity.ok(badges);
    }

    // get badge
    @GetMapping("/badge")
    public ResponseEntity<List<Badge>> getBadge(@RequestParam int id) {
        List<Badge> badges = badgeRepository.getBadge(id);
        if (badges != null && !badges.isEmpty()) {
            return ResponseEntity.ok(badges);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}