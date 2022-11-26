package com.qwerty.practice.dto;

import lombok.Data;
import javax.persistence.Id;
import java.util.Date;

@Data
public class ReviewDTO {
    @Id
    private Integer reviewid;
    private Integer parentid;
    private String content;
    private Double rating;
    private Date date_added;
    private Integer usersid;
    private Integer movieid;
}
