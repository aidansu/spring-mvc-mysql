package com.aidansu.demo.dao.impl;

import com.aidansu.demo.dao.UserDAO;
import com.aidansu.demo.model.User;
import com.aidansu.demo.model.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by aidan on 17-4-26.
 */
@Repository("UserDAO")
public class UserDAOImpl implements UserDAO{

    @Autowired
    private JdbcTemplate template;

    public UserDAOImpl(){
    }

    public UserDAOImpl(JdbcTemplate template) throws SQLException {
        this.template = template;
    }

    @Override
    public void insert(User user) {
        this.template.update("INSERT INTO t_user(username,password,telephone,create_time,last_login_time,update_time) VALUES(?,?,?,?,?,?)",
                user.getUsername(),user.getPassword(),user.getTelephone(),user.getCreateTime(),user.getLastLoginTime(),user.getUpdateTime());

    }

    @Override
    public void update(User user) {
        this.template.update("UPDATE t_user SET username=?,password=?,telephone=?,create_time=?,last_login_time=?,update_time=? WHERE id=?",
                user.getUsername(),user.getPassword(),user.getTelephone(),user.getCreateTime(),user.getLastLoginTime(),user.getUpdateTime(),
                user.getId());
    }

    @Override
    public void delete(long id) {
        this.template.update("DELETE FROM t_user WHERE id=?", id);
    }

    @Override
    public User findById(long id) {
        String sql = "SELECT * FROM t_user WHERE id=?";
        return this.template.queryForObject(sql, new Object[]{id}, new UserMapper());

    }

    @Override
    public User findByUsername(String username) {
        String sql = "SELECT id,username,password,telephone,create_time,last_login_time,update_time FROM t_user WHERE username=?";
        return this.template.queryForObject(sql, new Object[]{username}, new UserMapper());

    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM t_user";
        return this.template.query(sql, new Object[]{}, new UserMapper());
    }
}
