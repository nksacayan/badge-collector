package com.nsac.badgecollectorserver.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBadgeId implements Serializable {
    @Column(name = "user_id")
    private Integer userId;
    
    @Column(name = "badge_id")
    private Integer badgeId;
}