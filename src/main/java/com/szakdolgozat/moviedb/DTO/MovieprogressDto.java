package com.szakdolgozat.moviedb.DTO;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.szakdolgozat.moviedb.Entities.Movieprogress} entity
 */
@Data
public class MovieprogressDto implements Serializable {
    private final Integer id;
    private final Integer movid;
    private final Boolean favflag;
    private final Character flag;
}