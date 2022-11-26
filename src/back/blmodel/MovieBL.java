package com.qwerty.practice.blmodel;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Data
public class MovieBL {
    @Id
    private Integer movieid;
    private String title;
    private String overview;
    private DirectorBL director;
    private Date release_date;
    private Integer runtime;
    private Double vote_average;
    private String poster_path;
    List<GenreBL> genreList;
}
