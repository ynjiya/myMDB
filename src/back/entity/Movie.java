package com.qwerty.practice.entity;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "MOVIE")

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "movieid", scope = Movie.class)

public class Movie {
    @Id
    @Column(name = "MOVIEID")
    private Integer movieid;

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "wlid", scope = Movie.class)
    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy="movie")
    private List<Watchlist> watchlistList;

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "reviewid", scope = Movie.class)
    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy="movie")
    private List<Review> reviewList;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "OVERVIEW")
    private String overview;

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "directorid", scope = Movie.class)
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name="directorid", nullable=false)
    private Director director;

    @Column(name = "RELEASE_DATE")
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Ulaanbaatar")
    private Date release_date;

    @Column(name = "RUNTIME")
    private Integer runtime;

    @Column(name = "VOTE_AVERAGE")
    private Double vote_average;

    @Column(name = "POSTER_PATH")
    private String poster_path;

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "genreid", scope = Movie.class)
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToMany
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movieid"),
            inverseJoinColumns = @JoinColumn(name = "genreid"))
    List<Genre> genreList;
}
