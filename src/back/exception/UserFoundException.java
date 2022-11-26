package com.qwerty.practice.exception;

public class UserFoundException extends RuntimeException {
    public UserFoundException(String email) {
        super("User with email " + email + "exists");
    }
}
