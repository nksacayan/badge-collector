package com.nsac.badgecollectorserver.models;

import java.util.Set;
import java.util.stream.Collectors;

import com.nsac.badgecollectorserver.entities.Badge;
import com.nsac.badgecollectorserver.entities.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Integer id;
    private String name;
    private Set<BadgeDTO> badges;

    // Full mapper: includes badges (shallow)
    public static UserDTO fromEntity(User user) {
        if (user == null) return null;
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .badges(user.getBadges().stream()
                        .map(BadgeDTO::fromEntityShallow)
                        .collect(Collectors.toSet()))
                .build();
    }

    // Shallow mapper: excludes badges
    public static UserDTO fromEntityShallow(User user) {
        if (user == null) return null;
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }

    public User toEntity() {
        Set<Badge> badgeEntities = this.badges != null ? this.badges.stream()
                .map(BadgeDTO::toEntity)
                .collect(Collectors.toSet()) : null;

        return User.builder()
                .id(this.id)
                .name(this.name)
                .badges(badgeEntities)
                .build();
    }
}