package com.aidansu.demo.service;

import com.aidansu.demo.model.User;

import java.util.List;

/**
 * Created by aidan on 17-4-26.
 */
public interface UserService {

    void insert(User user);

    void update(User user);

    void delete(int id);

    User findById(int id);

    User findByUsername(String username);

    List<User> findAll();
}
