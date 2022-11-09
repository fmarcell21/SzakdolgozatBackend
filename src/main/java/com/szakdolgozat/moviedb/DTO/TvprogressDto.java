package com.szakdolgozat.moviedb.DTO;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.szakdolgozat.moviedb.Entities.Tvprogress} entity
 */
@Data
public class TvprogressDto implements Serializable {
    private final Integer id;
    private final Integer tvid;
    private final Boolean favflag;
    private final Character flag;
    private final Integer episodecount;
    private final Integer seasoncount;
}