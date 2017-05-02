package com.aidansu.demo.dao;

import com.aidansu.demo.model.User;

import java.util.List;

/**
 * Created by aidan on 17-4-26.
 */
public interface UserDAO {

    void insert(User user);

    void update(User user);

    void delete(long id);

    User findById(long id);

    User findByUsername(String username);

    List<User> findAll();

}
