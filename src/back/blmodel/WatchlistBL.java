package com.qwerty.practice.blmodel;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class WatchlistBL {
    @Id
    private Integer wlid;
    private Date date_added;
    private Integer usersid;
    private Integer movieid;
}
