package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.entity.UserRole;
import com.example.model.AuthenticationUserDetails;
import com.example.repository.UserRepository;
import com.example.repository.UserRoleRepository;

@Service
public class AuthenticationUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	User user = userRepository.findByUsername(username).orElseThrow();
        UserRole userRole = userRoleRepository.findByuser(user);
        return new AuthenticationUserDetails(user, userRole);
    }
}