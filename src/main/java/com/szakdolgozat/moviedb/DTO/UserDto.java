package com.szakdolgozat.moviedb.DTO;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * A DTO for the {@link com.szakdolgozat.moviedb.Entities.User} entity
 */
@Data
public class UserDto implements Serializable {
    private final Integer id;
    private final String username;
    private final String email;
    private final String password;
    private final Set<MovieprogressDto> movieprogresses;
    private final Set<PersonDto> people;
    private final Set<TvprogressDto> tvprogresses;
}