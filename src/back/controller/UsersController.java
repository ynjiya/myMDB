package com.qwerty.practice.controller;

import com.qwerty.practice.dto.UsersDTO;
import com.qwerty.practice.dto.UsersLoginDTO;
import com.qwerty.practice.dto.UsersPasswordDTO;
import com.qwerty.practice.dto.UsersPostDTO;
import com.qwerty.practice.exception.UserFoundException;
import com.qwerty.practice.exception.UserNotFoundException;
import com.qwerty.practice.mapper.UsersMapper;
import com.qwerty.practice.service.UsersService;
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
public class UsersController {
    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    UsersMapper usersMapper;
    @Autowired
    UsersService usersService;


//    get user details after logging in with username and password
    @PostMapping(value = "/login")
    public ResponseEntity<UsersDTO> getOneUser(@RequestBody UsersLoginDTO user) throws UserNotFoundException {
        logger.info("POST /login from getOneUser() {}", user);
        return ResponseEntity.ok(usersMapper.blToDto(usersService.getOneUser(user)));
    }

// register
    @PostMapping(value = "/register")
    public ResponseEntity<UsersDTO> addUser(@RequestBody UsersPostDTO user) throws UserFoundException {
        logger.info("POST /register from addUser() {}", user);
        return new ResponseEntity<>(usersMapper.blToDto(usersService.addUser(usersMapper.dtoToBl(user))), HttpStatus.OK);
    }

    @DeleteMapping(value = "/users/{userid}")
    public void deleteUser(@PathVariable Integer userid) {
        logger.info("DELETE /users/{} from deleteUser()", userid);
        usersService.deleteUser(userid);
    }

    @GetMapping(value = "/users/{userid}")
    public ResponseEntity<UsersDTO> getUserByUserid(@PathVariable Integer userid) {
        logger.info("GET /users/{} from getUserByUserid()", userid);
        return new ResponseEntity<>(usersMapper.blToDto(usersService.getUserByUserid(userid)), HttpStatus.OK);
    }

    @PatchMapping(value = "/users/{userid}")
    public void changePassword(@RequestBody UsersPasswordDTO upDTO, @PathVariable Integer userid) {
        logger.info("PATCH /users/{} from changePassword()", userid);
        usersService.change_password(userid, upDTO.getNewPassword(), upDTO.getOldPassword());
    }

    @PutMapping(value = "/users/{userid}")
    public void profileSettings(@RequestBody UsersPostDTO user, @PathVariable Integer userid) {
        logger.info("PUT /users/{} from profileSettings()", userid);
        usersService.profile_settings(usersMapper.dtoToBl(user), userid);
    }
}
