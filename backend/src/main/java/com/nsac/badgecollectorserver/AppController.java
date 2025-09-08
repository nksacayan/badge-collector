package com.nsac.badgecollectorserver;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

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

    // user exists
    @GetMapping("/user/exists")
    public Boolean userExists(@RequestParam String name) {
        return userRepository.userExists(name);
    }

    // register user
    @GetMapping("/user/create")
    public User createUser(@RequestParam String name) {
        return userRepository.createUser(name);
    }

    // login user
    @GetMapping("/user/login")
    public User loginUser(@RequestParam String name) {
        return userRepository.getUser(name);
    }

    // add badge to user
    @GetMapping("/user/addBadge")
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