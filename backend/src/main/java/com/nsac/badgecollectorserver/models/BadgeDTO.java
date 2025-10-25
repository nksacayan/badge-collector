package com.nsac.badgecollectorserver.models;

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

	public static BadgeDTO fromEntity(Badge badge) {
		if (badge == null) return null;
		return BadgeDTO.builder()
				.id(badge.getId())
				.name(badge.getName())
				.description(badge.getDescription())
				.build();
	}

	public Badge toEntity() {
		return Badge.builder()
				.id(this.id)
				.name(this.name)
				.description(this.description)
				.build();
	}
}
