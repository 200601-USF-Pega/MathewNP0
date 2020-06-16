package com.revature.libraryconsoleapp.dao;

import com.revature.libraryconsoleapp.models.User;

import java.util.List;

public interface IUserRepo {
    public User addUser(User user);
    public void deleteUser(User user);
    public List<User> getAllUsers();
}
