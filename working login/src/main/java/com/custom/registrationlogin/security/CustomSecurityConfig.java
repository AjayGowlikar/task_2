package com.custom.registrationlogin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class CustomSecurityConfig
{
//    /api/v1/user

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        System.out.println("passwordEncoder");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        System.out.println("userDetailsService");
        return new CustomUserDetailsService();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        System.out.println("daoAuthenticationProvider");
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf->csrf.disable());
        httpSecurity.authorizeHttpRequests(
                configurer->configurer.requestMatchers("/login","/register","/forgot_password","/reset_password","/save").permitAll()
                        .anyRequest().authenticated()
        ).formLogin( form ->
                form.loginPage("/login")
                        .loginProcessingUrl("/userLogin")
                        .failureUrl("/fail")
                        .defaultSuccessUrl("/home")
                        .permitAll()
        ).logout(logout->logout.logoutUrl("/logout").logoutSuccessUrl("/login").permitAll());



        return httpSecurity.build();
    }


}
