package com.szakdolgozat.moviedb.Security;

import com.szakdolgozat.moviedb.Entities.User;
import com.szakdolgozat.moviedb.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username){
        User user = userRepository.findByUsername(username);
        return UserDetailsImplement.build(user);
    }
}
