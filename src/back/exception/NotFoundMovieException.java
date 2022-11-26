package com.qwerty.practice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(code = HttpStatus.BAD_REQUEST)
//https://developer.mozilla.org/en-US/docs/Web/HTTP/Status#client_error_responses
//https://www.baeldung.com/spring-response-status
public class NotFoundMovieException extends RuntimeException {
    public NotFoundMovieException(Integer movieid) {
        super("Movie with id " + movieid + "doesnt exist in DB");
    }
}
