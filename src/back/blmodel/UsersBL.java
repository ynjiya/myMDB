package com.qwerty.practice.blmodel;

import lombok.Data;

import javax.persistence.Id;

@Data
public class UsersBL {
    @Id
    private Integer userid;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
}

