package com.nsac.badgecollectorserver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BadgeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public java.util.List<Badge> findAll() {
        String sql = "SELECT id, name, description FROM badge";
        return jdbcTemplate.query(sql, (rs, _) ->
            new Badge(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("description")
            )
        );
    }

    private List<Badge> getBadgeBy(String column, Object value) {
    String sql = "SELECT id, name, description FROM badge WHERE " + column + " = ?";
    return jdbcTemplate.query(sql, badgeRowMapper(), value);
}

    private RowMapper<Badge> badgeRowMapper() {
        return (rs, _) -> new Badge(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("description")
        );
    }

    public List<Badge> getBadge(int id) {
        return getBadgeBy("id", id);
    }

    public List<Badge> getBadge(String name) {
        return getBadgeBy("name", name);
    }

}
