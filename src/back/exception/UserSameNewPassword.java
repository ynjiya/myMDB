package com.qwerty.practice.exception;

public class UserSameNewPassword extends RuntimeException {
    public UserSameNewPassword() {
        super("New password matches current password");
    }
}

