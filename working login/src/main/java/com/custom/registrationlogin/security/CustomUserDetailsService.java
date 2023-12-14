package com.custom.registrationlogin.security;

import com.custom.registrationlogin.dao.UserJpaRepository;
import com.custom.registrationlogin.entity.User;
import com.custom.registrationlogin.service.UserDao;
import com.custom.registrationlogin.service.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService
{
    @Autowired
    private UserDaoImpl userRepo;


    public CustomUserDetailsService(){}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepo.getUserByUsername(username);
        System.out.println(user);
        if(user==null)
        {
            throw new UsernameNotFoundException("User with username : " + username + " not exists.");
        }
        else
        {
            return new CustomUser(user);
        }
    }
}
