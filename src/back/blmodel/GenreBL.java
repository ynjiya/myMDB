package com.qwerty.practice.blmodel;

import lombok.Data;

import javax.persistence.Id;

@Data
public class GenreBL {
    @Id
    private Integer genreid;
    private String genrename;
}
