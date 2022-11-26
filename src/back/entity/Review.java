package com.qwerty.practice.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "REVIEW_COMMENT")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq")
    @GenericGenerator(name = "seq", strategy="increment")
    @Column(name = "REVIEWID")
    private Integer reviewid;

    @Column(name = "PARENTID")
    private Integer parentid;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "RATING")
    private Double rating;

    @Column(name = "DATE_ADDED")
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Ulaanbaatar")
    private Date date_added;

    @ManyToOne
    @JoinColumn(name="userid", nullable=false)
    private Users users;

    @ManyToOne
    @JoinColumn(name="movieid", nullable=false)
    @JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "movieid", scope = Review.class)
    @JsonIdentityReference(alwaysAsId = true)
    private Movie movie;
}
