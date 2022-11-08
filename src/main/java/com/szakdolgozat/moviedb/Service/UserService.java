package com.szakdolgozat.moviedb.Service;

import com.szakdolgozat.moviedb.DTO.UserDto;
import com.szakdolgozat.moviedb.Entities.User;
import com.szakdolgozat.moviedb.Mappers.UserMapper;
import com.szakdolgozat.moviedb.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.management.openmbean.KeyAlreadyExistsException;
import javax.persistence.EntityNotFoundException;


@Component
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

   /* @Autowired
    private UserMapper userMapper;*/

    public void createUser(UserDto userDto){

        userRepository.save(new User(userDto.getUsername(), userDto.getEmail(), userDto.getPassword()));

    }



}
