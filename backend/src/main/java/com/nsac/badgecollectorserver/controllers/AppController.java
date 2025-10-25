package com.nsac.badgecollectorserver.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nsac.badgecollectorserver.entities.Badge;
import com.nsac.badgecollectorserver.entities.User;
import com.nsac.badgecollectorserver.repositories.BadgeRepository;
import com.nsac.badgecollectorserver.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AppController {

    private final UserRepository userRepository;
    private final BadgeRepository badgeRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> users() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    // create user
    @PostMapping("/user/{name}")
    public ResponseEntity<User> createUser(@PathVariable String name) {
        User user = User.builder().name(name).build();
        return ResponseEntity.ok(userRepository.save(user));
    }

    // get user
    @GetMapping("/user/{name}")
    public ResponseEntity<User> loginUser(@PathVariable String name) {
        return userRepository.findByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // add badge to user
    @GetMapping("/user/{userId}/add-badge/{badgeId}")
    public ResponseEntity<User> addBadgeToUser(@PathVariable int userId, @PathVariable int badgeId) {
        return userRepository.findById(userId)
                .flatMap(user -> badgeRepository.findById(badgeId)
                        .map(badge -> {
                            user.addBadge(badge);
                            return ResponseEntity.ok(userRepository.save(user));
                        }))
                .orElse(ResponseEntity.notFound().build());
    }

    // get all badges
    @GetMapping("/badges")
    public ResponseEntity<List<Badge>> getAllBadges() {
        List<Badge> badges = badgeRepository.findAll();
        return ResponseEntity.ok(badges);
    }

    // get badge
    @GetMapping("/badge")
    public ResponseEntity<Badge> getBadge(@RequestParam int id) {
        return badgeRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}