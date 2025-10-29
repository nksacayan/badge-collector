package com.nsac.badgecollectorserver.models;

import java.util.Set;
import java.util.stream.Collectors;

import com.nsac.badgecollectorserver.entities.Badge;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BadgeDTO {
    private Integer id;
    private String name;
    private String description;
    private String imageFilename;
    private boolean secret;
    private Set<UserDTO> users;

    // Full mapper: includes users (shallow)
    public static BadgeDTO fromEntity(Badge badge) {
        if (badge == null) return null;
        return BadgeDTO.builder()
                .id(badge.getId())
                .name(badge.getName())
                .description(badge.getDescription())
                .imageFilename(badge.getImageFilename())
                .secret(badge.isSecret())
                .users(badge.getUsers().stream()
                        .map(UserDTO::fromEntityShallow)
                        .collect(Collectors.toSet()))
                .build();
    }

    // Shallow mapper: excludes users
    public static BadgeDTO fromEntityShallow(Badge badge) {
        if (badge == null) return null;
        return BadgeDTO.builder()
                .id(badge.getId())
                .name(badge.getName())
                .description(badge.getDescription())
                .imageFilename(badge.getImageFilename())
                .secret(badge.isSecret())
                .build();
    }

    public Badge toEntity() {
        return Badge.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .imageFilename(this.imageFilename)
                .secret(this.secret)
                .build();
    }
}