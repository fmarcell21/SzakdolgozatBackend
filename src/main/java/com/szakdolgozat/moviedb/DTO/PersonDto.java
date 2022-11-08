package com.szakdolgozat.moviedb.DTO;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.szakdolgozat.moviedb.Entities.Person} entity
 */
@Data
public class PersonDto implements Serializable {
    private final Integer id;
    private final Integer perid;
    private final Boolean favflag;
}