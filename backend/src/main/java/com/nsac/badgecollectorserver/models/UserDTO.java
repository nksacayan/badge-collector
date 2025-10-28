package com.nsac.badgecollectorserver.models;

import java.util.HashSet;
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
    @Builder.Default
    private Set<BadgeDTO> badges = new HashSet<>();
    
    public static UserDTO fromEntity(User user) {
        Set<BadgeDTO> badgeDTOs = user.getBadges().stream()
            .map(BadgeDTO::fromEntity)
            .collect(Collectors.toSet());

        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .badges(badgeDTOs)
                .build();
    }

    public User toEntity() {
        Set<Badge> badgeEntities = this.badges.stream()
            .map(BadgeDTO::toEntity)
            .collect(Collectors.toSet());

        return User.builder()
                .id(this.id)
                .name(this.name)
                .badges(badgeEntities)
                .build();
    }

}
