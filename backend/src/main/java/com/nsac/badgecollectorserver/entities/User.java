package com.nsac.badgecollectorserver.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "user")
    @Builder.Default
    private Set<UserBadge> userBadges = new HashSet<>();

    public void addBadge(Badge badge) {
        UserBadge userBadge = UserBadge.builder()
            .id(new UserBadgeId(this.getId(), badge.getId()))
            .user(this)
            .badge(badge)
            .build();
        userBadges.add(userBadge);
    }
}
