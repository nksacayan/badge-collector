package com.nsac.badgecollectorserver;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://127.0.0.1:5173") // frontend URL
public class AppController {

    private final UserRepository userRepository;
    private final BadgeRepository badgeRepository;

    public AppController(UserRepository userRepository, BadgeRepository badgeRepository) {
        this.userRepository = userRepository;
        this.badgeRepository = badgeRepository;
    }

    @GetMapping("/users")
    public List<User> users() {
        return userRepository.findAll();
    }

    // create user
    @GetMapping("/user/create")
    public User createUser(@RequestParam String name) {
        return userRepository.createUser(name);
    }

    // get user
    @GetMapping("/user")
    public User loginUser(@RequestParam String name) {
        return userRepository.getUser(name);
    }

    // add badge to user
    @GetMapping("/user/add-badge")
    public User addBadgeToUser(@RequestParam int userId, @RequestParam int badgeId) {
        return userRepository.addBadgeToUser(userId, badgeId);
    }

    // get all badges
    @GetMapping("/badges")
    public List<Badge> getAllBadges() {
        return badgeRepository.findAll();
    }

    // get badge
    @GetMapping("/badge")
    public List<Badge> getBadge(@RequestParam int id) {
        return badgeRepository.getBadge(id);
    }
}