package com.qwerty.practice.dto;

import lombok.Data;
import javax.persistence.Id;

@Data
public class UsersDTO {
    @Id
    private Integer userid;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
//    private String password;
}

