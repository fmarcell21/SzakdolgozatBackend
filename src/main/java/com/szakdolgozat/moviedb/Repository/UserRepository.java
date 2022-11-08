package com.szakdolgozat.moviedb.Repository;

import com.szakdolgozat.moviedb.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByUsername(String username);
    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);
    //boolean isUsernameTaken(String username);




}