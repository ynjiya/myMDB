package com.qwerty.practice.dto;

import lombok.Data;
import javax.persistence.Id;

@Data
public class DirectorDTO {
    @Id
    private Integer directorid;
    private String directorname;
    private Integer gender;
    private Double popularity;
}
