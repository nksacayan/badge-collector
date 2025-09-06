package com.nsac.badgecollectorserver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

    public boolean userExists(String name) {
        Integer count = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM users WHERE name = ?",
            Integer.class,
            name
        );
        return count != null && count > 0;
    }

    public User createUser(String name) {
        // Insert user and get generated id
        jdbcTemplate.update("INSERT INTO users (name) VALUES (?)", name);
        Integer id = jdbcTemplate.queryForObject(
            "SELECT id FROM users WHERE name = ? ORDER BY id DESC LIMIT 1",
            Integer.class,
            name
        );
        if (id == null) {
            throw new RuntimeException("Failed to create user");
        }
        return new User(id, name, new int[]{});
    }

    private User getUserBy(String column, Object value) {
        String sql = "SELECT id, name FROM users WHERE " + column + " = ?";
        List<User> users = jdbcTemplate.query(sql, userRowMapper(), value);
        return users.isEmpty() ? null : users.get(0);
    }

    private RowMapper<User> userRowMapper() {
        return (rs, _) -> {
            int userId = rs.getInt("id");
            String name = rs.getString("name");

            List<Integer> badgeList = jdbcTemplate.queryForList(
                "SELECT badge_id FROM user_badges WHERE user_id = ?",
                Integer.class,
                userId
            );

            int[] badgeIds = badgeList.stream().mapToInt(Integer::intValue).toArray();
            return new User(userId, name, badgeIds);
        };
    }

    public User getUser(int id) {
        return getUserBy("id", id);
    }

    public User getUser(String name) {
        return getUserBy("name", name);
    }
}
