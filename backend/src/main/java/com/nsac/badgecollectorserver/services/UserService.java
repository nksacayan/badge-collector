package com.nsac.badgecollectorserver.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nsac.badgecollectorserver.entities.User;
import com.nsac.badgecollectorserver.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User findUserByName(String name) {
        return userRepository.findByName(name)
            .orElseThrow(() -> new RuntimeException("User not found: " + name));
    }
}
