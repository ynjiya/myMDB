package com.qwerty.practice.exception;

public class NotFoundInWatchlistException extends RuntimeException {
    public NotFoundInWatchlistException(Integer userId) {
        super("User" + userId + " doesn't have Watchlist");
    }
}
