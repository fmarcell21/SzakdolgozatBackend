package com.szakdolgozat.moviedb.Controllers;

import com.szakdolgozat.moviedb.DTO.Security.Request.LoginRequest;
import com.szakdolgozat.moviedb.DTO.Security.Request.RegisterRequest;
import com.szakdolgozat.moviedb.DTO.Security.Response.JwtResponse;
import com.szakdolgozat.moviedb.DTO.UserDto;
import com.szakdolgozat.moviedb.Entities.User;
import com.szakdolgozat.moviedb.Repository.UserRepository;
import com.szakdolgozat.moviedb.Security.JwtUtils;
import com.szakdolgozat.moviedb.Security.UserDetailsImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.szakdolgozat.moviedb.DTO.Security.Response.msgResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/secure")
public class SecureController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;


    @PostMapping(path = "/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest.getUsername());
        System.out.println(loginRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        System.out.println("JWT Token:"+jwt);

        UserDetailsImplement userDetails = (UserDetailsImplement) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getUser().getUsername(),
                userDetails.getUser().getEmail(),
                userDetails.getUser().getId()
                ));
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest){
        if(userRepository.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new msgResponse("This username is taken!"));
        }
        if(userRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new msgResponse("This email is taken!"));
        }

        System.out.println(registerRequest.getEmail());
        System.out.println(registerRequest.getUsername());
        System.out.println(registerRequest.getPassword());

        User newUser = new User();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setEmail(registerRequest.getEmail());

        newUser.setPassword(encoder.encode(registerRequest.getPassword()));
        System.out.println("Teszt");
        userRepository.save(newUser);


        return ResponseEntity.ok(new msgResponse("Registration successful!"));
    }
}
