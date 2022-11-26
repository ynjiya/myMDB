package com.qwerty.practice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Data
@Table(name = "GENRE")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "genreid", scope = Genre.class)
//@JsonIdentityReference(alwaysAsId = true)
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GENREID")
    private Integer genreid;

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "movieid", scope = Genre.class)
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToMany(mappedBy = "genreList")
    List<Movie> movieList;

    @Column(name = "GENRENAME")
    private String genrename;

}
