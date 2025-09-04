package com.nsac.badgecollectorserver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> findAll() {
        // First, fetch all users
        List<User> users = jdbcTemplate.query(
            "SELECT id, name FROM users",
            (rs, _) -> {
                int userId = rs.getInt("id");
                String name = rs.getString("name");

                // Fetch badge IDs for this user
                List<Integer> badgeList = jdbcTemplate.queryForList(
                    "SELECT badge_id FROM user_badges WHERE user_id = ?",
                    Integer.class,
                    userId
                );

                int[] badgeIds = badgeList.stream().mapToInt(Integer::intValue).toArray();

                return new User(userId, name, badgeIds);
            }
        );

        return users;
    }
}
