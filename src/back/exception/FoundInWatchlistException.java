package com.qwerty.practice.exception;

public class FoundInWatchlistException extends RuntimeException {
    public FoundInWatchlistException(Integer movieId, Integer userId) {
        super("Movie with id " + movieId + "already exists in user" + userId + "'s Watchlist");
    }
}
