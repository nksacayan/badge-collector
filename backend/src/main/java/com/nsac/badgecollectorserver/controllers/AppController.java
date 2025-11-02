package com.nsac.badgecollectorserver.controllers;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nsac.badgecollectorserver.exceptions.UserNotFoundException;
import com.nsac.badgecollectorserver.models.AuthResponse;
import com.nsac.badgecollectorserver.models.BadgeDTO;
import com.nsac.badgecollectorserver.models.UserDTO;
import com.nsac.badgecollectorserver.services.BadgeService;
import com.nsac.badgecollectorserver.services.JWTService;
import com.nsac.badgecollectorserver.services.UserService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AppController {

    private final UserService userService;
    private final BadgeService badgeService;
    private final JWTService jwtService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> users() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/user/{name}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String name) {
        try {
            return ResponseEntity.ok(userService.findUserByName(name));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/user/register/{name}")
    public ResponseEntity<AuthResponse> registerUser(@PathVariable String name) {
        UserDTO userDTO = userService.createUser(name);
        String jwt = jwtService.generateTokenWithUserId(userDTO);
        AuthResponse authResponse = new AuthResponse(userDTO, jwt);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/user/login/{name}")
    public ResponseEntity<AuthResponse> loginUser(@PathVariable String name) {
        try {
            UserDTO userDTO = userService.findUserByName(name);
            String jwt = jwtService.generateTokenWithUserId(userDTO);
            AuthResponse authResponse = new AuthResponse(userDTO, jwt);
            return ResponseEntity.ok(authResponse);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/user/{userId}/add-badge/{badgeId}")
    public ResponseEntity<UserDTO> addBadgeToUser(@PathVariable int userId, @PathVariable int badgeId) {
        try {
            return ResponseEntity.ok(userService.addBadgeToUser(userId, badgeId));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Profile("dev")
    @PostMapping("nfc/add-badge/{badgeId}")
    public ResponseEntity<UserDTO> addBadgeToUserWithJwt(@PathVariable int badgeId, @RequestBody String jwt) {
        int userId = Integer.parseInt(jwtService.extractTokenSubject(jwt));
        return addBadgeToUser(userId, badgeId);
    }

    @PostMapping("nfc/add-badge/{badgeId}/nfc-id/{nfcId}")
    public ResponseEntity<UserDTO> addBadgeToUserWithJwtAndNfcId(@PathVariable int badgeId, @PathVariable String nfcId, @RequestBody String jwt) {
        int userId = Integer.parseInt(jwtService.extractTokenSubject(jwt));
        boolean badgeIdAndNfcIdMatch = badgeService.doesBadgeIdMatchNfcId(badgeId, nfcId);
        if (badgeIdAndNfcIdMatch) {
            return addBadgeToUser(userId, badgeId);
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/badges")
    public ResponseEntity<List<BadgeDTO>> getAllBadges() {
        return ResponseEntity.ok(badgeService.getAllBadges());
    }

    @GetMapping("/badge/{badgeId}")
    public ResponseEntity<BadgeDTO> getBadge(@PathVariable int badgeId) {
        try {
            return ResponseEntity.ok(badgeService.getBadgeById(badgeId));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/ping")
    public ResponseEntity<String> getPing() {
        return ResponseEntity.ok("Pong");
    }
}