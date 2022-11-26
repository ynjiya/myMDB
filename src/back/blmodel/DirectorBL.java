package com.qwerty.practice.blmodel;

import lombok.Data;

import javax.persistence.Id;

@Data
public class DirectorBL {
    @Id
    private Integer directorid;
    private String directorname;
    private Integer gender;
    private Double popularity;
}
