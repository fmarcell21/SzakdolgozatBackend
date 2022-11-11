package com.szakdolgozat.moviedb.Repository;

import com.szakdolgozat.moviedb.Entities.ERole;

import com.szakdolgozat.moviedb.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole role);
}
