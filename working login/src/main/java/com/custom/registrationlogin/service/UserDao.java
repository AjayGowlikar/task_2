package com.custom.registrationlogin.service;

import com.custom.registrationlogin.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserDao
{
    List<User> getAllUsers();

    User getUserById(Long id);

    User getUserByUsername(String username);

    User addUser(User user);

    User updateUserDtls(User user);

    void deleteUserById(Long id);

    void deleteAllUser();
}
