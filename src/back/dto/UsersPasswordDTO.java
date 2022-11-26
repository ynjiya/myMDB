package com.qwerty.practice.dto;
import lombok.Data;

@Data
public class UsersPasswordDTO {
    private String newPassword;
    private String oldPassword;
}
