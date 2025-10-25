package com.nsac.badgecollectorserver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nsac.badgecollectorserver.entities.Badge;
import com.nsac.badgecollectorserver.entities.User;
import com.nsac.badgecollectorserver.models.UserDTO;
import com.nsac.badgecollectorserver.repositories.BadgeRepository;
import com.nsac.badgecollectorserver.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BadgeRepository badgeRepository;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
            .map(UserDTO::fromEntity)
            .collect(Collectors.toList());
    }

    public UserDTO createUser(String name) {
        User user = User.builder().name(name).build();
        return UserDTO.fromEntity(userRepository.save(user));
    }

    public UserDTO findUserByName(String name) {
        return userRepository.findByName(name)
            .map(UserDTO::fromEntity)
            .orElseThrow(() -> new RuntimeException("User not found: " + name));
    }

    public UserDTO findUserById(Integer id) {
        return userRepository.findById(id)
            .map(UserDTO::fromEntity)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public UserDTO addBadgeToUser(Integer userId, Integer badgeId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        
        Badge badge = badgeRepository.findById(badgeId)
            .orElseThrow(() -> new RuntimeException("Badge not found with id: " + badgeId));

        user.addBadge(badge);
        return UserDTO.fromEntity(userRepository.save(user));
    }
}
