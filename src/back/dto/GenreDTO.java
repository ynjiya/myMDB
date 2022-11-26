package com.qwerty.practice.dto;

import lombok.Data;
import javax.persistence.Id;

@Data
public class GenreDTO {
    @Id
    private Integer genreid;
    private String genrename;
}
