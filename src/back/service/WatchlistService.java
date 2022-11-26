package com.qwerty.practice.service;

import com.qwerty.practice.blmodel.WatchlistBL;
import com.qwerty.practice.dto.WatchlistDTO;
import com.qwerty.practice.entity.Movie;
import com.qwerty.practice.entity.Watchlist;
import com.qwerty.practice.exception.FoundInWatchlistException;
import com.qwerty.practice.exception.NotFoundInWatchlistException;
import com.qwerty.practice.mapper.WatchlistMapper;
import com.qwerty.practice.repository.MovieRepo;
import com.qwerty.practice.repository.UsersRepo;
import com.qwerty.practice.repository.WatchlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost"}, allowCredentials = "true")
public class WatchlistService {

    @Autowired
    WatchlistMapper watchlistMapper;
    @Autowired
    WatchlistRepo watchlistRepo;

    @Autowired
    MovieRepo movieRepo;

    @Autowired
    UsersRepo usersRepo;


    public WatchlistBL addToWatchlist(WatchlistBL watchlist) {

        Integer tmp_movieId = watchlist.getMovieid();
        Integer tmp_userId = watchlist.getUsersid();


        if (watchlistRepo.findByMovieMovieidAndUsersUserid(tmp_movieId, tmp_userId) == null) {
            return watchlistMapper.entityToBl(watchlistRepo.save(watchlistMapper.blToEntity(watchlist, movieRepo, usersRepo)));
        }
        throw new FoundInWatchlistException(tmp_movieId, tmp_userId);
    }

    public void deleteFromWatchlist(Integer userId, Integer movieId) {
        watchlistRepo.deleteByMovieidAndUserid(userId, movieId);
    }
}










