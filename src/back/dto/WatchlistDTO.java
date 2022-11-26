package com.qwerty.practice.dto;

import lombok.Data;
import javax.persistence.Id;
import java.util.Date;

@Data
public class WatchlistDTO {
    @Id
    private Integer wlid;
    private Date date_added;
    private Integer usersid;
    private Integer movieid;
}
