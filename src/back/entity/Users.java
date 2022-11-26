package com.qwerty.practice.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "USERS")

public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq")
    @GenericGenerator(name = "seq", strategy="increment")
    @Column(name = "USERID")
    private Integer userid;

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "wlid",
            scope = Users.class)
    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy="users", fetch = FetchType.LAZY)
    private List<Watchlist> watchlistList;

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "reviewid",
            scope = Users.class)
    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy="users")
    private  List<Review> reviewList;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "FIRSTNAME")
    private String firstname;

    @Column(name = "LASTNAME")
    private String lastname;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;
}

