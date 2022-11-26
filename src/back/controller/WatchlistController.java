package com.qwerty.practice.controller;

import com.qwerty.practice.dto.WatchlistDTO;
import com.qwerty.practice.dto.WatchlistPostDTO;
import com.qwerty.practice.exception.FoundInWatchlistException;
import com.qwerty.practice.exception.NotFoundInWatchlistException;
import com.qwerty.practice.blmodel.WatchlistBL;
import com.qwerty.practice.mapper.WatchlistMapper;
import com.qwerty.practice.service.WatchlistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path="/api")

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost"}, allowCredentials = "true")
public class WatchlistController {
    private static final Logger logger = LoggerFactory.getLogger(WatchlistController.class);

    @Autowired
    WatchlistMapper watchlistMapper;
    @Autowired
    WatchlistService watchlistService;


    @PostMapping(value = "/watchlists")
    public ResponseEntity<WatchlistDTO> addToWatchlist(@RequestBody WatchlistPostDTO watchlist) throws FoundInWatchlistException {
        logger.info("POST /watchlists from addToWatchlist() {}", watchlist);
        return new ResponseEntity<>(watchlistMapper.blToDto(watchlistService.addToWatchlist(watchlistMapper.dtoToBl(watchlist))), HttpStatus.OK);
    }

    @DeleteMapping(value = "/watchlists/{wlid}/movies/{movieid}")
    public void deleteFromWatchlist(@PathVariable Integer wlid, @PathVariable Integer movieid) {
        logger.info("DELETE /watchlists/{}/{} from deleteFromWatchlist() by userid {} and movieid {}", wlid, movieid, wlid, movieid);
        watchlistService.deleteFromWatchlist(wlid, movieid);
    }
}
