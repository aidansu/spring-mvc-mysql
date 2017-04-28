package com.aidansu.demo.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by aidan on 17-4-26.
 */
public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setTelephone(rs.getString("telephone"));
        user.setCreateTime(rs.getDate("create_time"));
        user.setLastLoginTime(rs.getDate("last_login_time"));
        user.setUpdateTime(rs.getDate("update_time"));
        return user;
    }
}
