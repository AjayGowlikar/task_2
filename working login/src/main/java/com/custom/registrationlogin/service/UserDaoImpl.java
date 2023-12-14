package com.custom.registrationlogin.service;

import com.custom.registrationlogin.dao.UserJpaRepository;
import com.custom.registrationlogin.encoding.PasswordEncoder;
import com.custom.registrationlogin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDaoImpl implements UserDao
{
    private UserJpaRepository userJpaRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserDaoImpl(UserJpaRepository userJpaRepository, BCryptPasswordEncoder passwordEncoder)
    {
        this.userJpaRepository = userJpaRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public List<User> getAllUsers()
    {
        return userJpaRepository.findAll();
    }

    @Override
    public User getUserById(Long id)
    {
        User user = userJpaRepository.findById(id).get();
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        System.out.println(userJpaRepository.findByUsername(username));
        return userJpaRepository.findByUsername(username);
    }

    @Override
    public User addUser(User user)
    {
//        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userJpaRepository.save(user);
    }

    @Override
    public User updateUserDtls(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userJpaRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userJpaRepository.deleteById(id);
    }

    @Override
    public void deleteAllUser() {
        userJpaRepository.deleteAll();
    }

    public void  updateResetPasswordToken(String token,String email){
        User user = userJpaRepository.findByEmail(email);
        if (user!=null){
            user.setResetPasswordToken(token);
            userJpaRepository.save(user);
        }else {
            throw new UsernameNotFoundException("Could not find any email with " + email);
        }
    }

    public User getByResetPasswordToken(String token) {
        return userJpaRepository.findByResetPasswordToken(token);
    }

    public void updatePassword(User user, String newPassword){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        user.setResetPasswordToken(null);

        userJpaRepository.save(user);



    }

}
