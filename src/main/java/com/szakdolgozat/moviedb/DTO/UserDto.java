package com.szakdolgozat.moviedb.DTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * A DTO for the {@link com.szakdolgozat.moviedb.Entities.User} entity
 */
@Data
public class UserDto implements Serializable {
    private final Integer id;

    @NotNull
    @NotEmpty
    private final String username;

    @NotNull
    @NotEmpty
    private final String email;

    @NotNull
    @NotEmpty
    private final String password;

    private final Set<MovieprogressDto> movieprogresses;
    private final Set<PersonDto> people;
    private final Set<TvprogressDto> tvprogresses;
}