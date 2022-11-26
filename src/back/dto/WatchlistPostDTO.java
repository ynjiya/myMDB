package com.qwerty.practice.dto;

import lombok.Data;
import java.util.Date;

@Data
public class WatchlistPostDTO {
    private Date date_added;
    private Integer usersid;
    private Integer movieid;
}
