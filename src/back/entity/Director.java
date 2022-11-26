package com.qwerty.practice.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "DIRECTOR")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "directorid",scope = Director.class)
//@JsonIdentityReference(alwaysAsId = true)
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DIRECTORID")
    private Integer directorid;

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "movieid", scope = Director.class)
    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy="director")
    private List<Movie> movieList;

    @Column(name = "DIRECTORNAME")
    private String directorname;

    @Column(name = "GENDER")
    private Integer gender;

    @Column(name = "POPULARITY")
    private Double popularity;

}
