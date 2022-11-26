package com.qwerty.practice.dto;

import lombok.Data;
import java.util.Date;

@Data
public class ReviewPostDTO {
    private Integer parentid;
    private String content;
    private Double rating;
    private Date date_added;
    private Integer usersid;
    private Integer movieid;
}
