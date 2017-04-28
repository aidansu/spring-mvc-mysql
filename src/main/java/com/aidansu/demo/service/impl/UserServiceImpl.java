package com.aidansu.demo.service.impl;

import com.aidansu.demo.dao.UserDAO;
import com.aidansu.demo.model.User;
import com.aidansu.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by aidan on 17-4-26.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO ;

    @Override
    public void insert(User user) {
        userDAO.insert(user);
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    public void delete(int id) {
        userDAO.delete(id);
    }

    @Override
    public User findById(int id) {
        return userDAO.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }
}
