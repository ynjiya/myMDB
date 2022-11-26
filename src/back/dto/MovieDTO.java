package com.qwerty.practice.dto;

import lombok.Data;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Data
public class MovieDTO {
    @Id
    private Integer movieid;
    private String title;
    private String overview;
    private DirectorDTO director;
    private Date release_date;
    private Integer runtime;
    private Double vote_average;
    private String poster_path;
    List<GenreDTO> genreList;
}
