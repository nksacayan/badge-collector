package com.nsac.badgecollectorserver.models;

import java.util.HashSet;
import java.util.Set;

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
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }

    public User toEntity() {
        return User.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }
}
