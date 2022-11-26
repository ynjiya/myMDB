package com.qwerty.practice.exception;

public class UserWrongOldPassword extends RuntimeException {
    public UserWrongOldPassword() {
        super("Old password doesnt match");
    }
}

