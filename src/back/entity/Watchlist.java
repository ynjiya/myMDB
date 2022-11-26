package com.qwerty.practice.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "WATCHLIST")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "wlid", scope = Watchlist.class)

public class Watchlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq")
    @GenericGenerator(name = "seq", strategy="increment")
    @Column(name = "WLID")
    private Integer wlid;


    @Column(name = "DATE_ADDED")
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Ulaanbaatar")
    private Date date_added;

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "userid", scope = Watchlist.class)
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userid")

    private Users users;

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "movieid", scope = Watchlist.class)
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name="movieid")
    private Movie movie;
}
