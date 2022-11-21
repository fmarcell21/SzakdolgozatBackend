package com.szakdolgozat.moviedb.Controllers;

import com.szakdolgozat.moviedb.Entities.User;
import com.szakdolgozat.moviedb.DTO.UserDto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import com.szakdolgozat.moviedb.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import com.szakdolgozat.moviedb.Mappers.UserMapper;
import com.szakdolgozat.moviedb.Entities.Movieprogress;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;
import com.szakdolgozat.moviedb.Service.UserService;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private  UserService userService;
    private final UserMapper userMapper;

    private final UserRepository userRepository;



    public UserController(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @GetMapping("/find/{username}")
    public Map<String, String> findByUsername(@PathVariable String username){
        User existingUser = userRepository.findByUsername(username);
        Map<String,String> map = new LinkedHashMap<>();

        String Id = existingUser.getId().toString();
        String Username = existingUser.getUsername();
        String Email = existingUser.getEmail();

        map.put("id", Id);
        map.put("username", Username);
        map.put("email", Email);

        return map;

    }

    /*@GetMapping("/new")
    public UserDto saveUser(@RequestBody @NonNull @Valid UserDto userDto){
        User userEntity = userMapper.userDtoToUser(userDto);
        return userMapper.userToUserDto(userRepository.save(userEntity));
    }*/

    /*@GetMapping("/update")
    public UserDto updateUser(@RequestBody @NonNull @Valid UserDto userDto){
        if(userDto.getId() == null){
            throw new IllegalArgumentException("No user found!");
        }
        User userEntity = userRepository.findById(userDto.getId())
                .orElseThrow(EntityNotFoundException::new);
        userMapper.updateUserFromUserDto(userDto, userEntity);

        //userService.updateUser(userDto);
        return userMapper.userToUserDto(userRepository.save(userEntity));
    }*/

    @GetMapping("/all")
    public List<UserDto> getAllUsers(){
        List<User> userList = userRepository.findAll();

        return userList.stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());
    }









   /* @GetMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserDto data){

        return ResponseEntity.ok("User updated!");
    }*/
}
