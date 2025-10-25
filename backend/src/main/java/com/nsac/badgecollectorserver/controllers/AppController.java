package com.nsac.badgecollectorserver.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nsac.badgecollectorserver.models.BadgeDTO;
import com.nsac.badgecollectorserver.models.UserDTO;
import com.nsac.badgecollectorserver.services.BadgeService;
import com.nsac.badgecollectorserver.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AppController {

    private final UserService userService;
    private final BadgeService badgeService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> users() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/user/{name}")
    public ResponseEntity<UserDTO> createUser(@PathVariable String name) {
        return ResponseEntity.ok(userService.createUser(name));
    }

    @GetMapping("/user/{name}")
    public ResponseEntity<UserDTO> loginUser(@PathVariable String name) {
        try {
            return ResponseEntity.ok(userService.findUserByName(name));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}/add-badge/{badgeId}")
    public ResponseEntity<UserDTO> addBadgeToUser(@PathVariable int userId, @PathVariable int badgeId) {
        try {
            return ResponseEntity.ok(userService.addBadgeToUser(userId, badgeId));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/badges")
    public ResponseEntity<List<BadgeDTO>> getAllBadges() {
        return ResponseEntity.ok(badgeService.getAllBadges());
    }

    @GetMapping("/badge")
    public ResponseEntity<BadgeDTO> getBadge(@RequestParam int id) {
        try {
            return ResponseEntity.ok(badgeService.getBadgeById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}