package com.qwerty.practice.service;
import com.qwerty.practice.blmodel.UsersBL;
import com.qwerty.practice.dto.UsersDTO;
import com.qwerty.practice.dto.UsersLoginDTO;
import com.qwerty.practice.entity.Users;
import com.qwerty.practice.exception.UserFoundException;
import com.qwerty.practice.exception.UserNotFoundException;
import com.qwerty.practice.exception.UserSameNewPassword;
import com.qwerty.practice.exception.UserWrongOldPassword;
import com.qwerty.practice.mapper.UsersMapper;
import com.qwerty.practice.repository.UsersRepo;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;


@Service

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost"}, allowCredentials = "true")
public class UsersService {
    @Autowired
    UsersMapper usersMapper;

    @Autowired
    UsersRepo usersRepo;

    public List<UsersBL> getAllUser() {
        return usersMapper.entitiesToBls(usersRepo.findAllBy());
    }

    public UsersBL getOneUser(UsersLoginDTO user) {
        Users tmp =  usersRepo.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (tmp == null) {
            throw new UserNotFoundException();
        }
        return usersMapper.entityToBl(tmp);
    }

    public UsersBL addUser(UsersBL persons) {
        String tmp_email = persons.getEmail();
        String tmp_username = persons.getUsername();

        if (usersRepo.findByEmail(tmp_email) == null && usersRepo.findByUsername(tmp_username) == null) {
            return usersMapper.entityToBl(usersRepo.save(usersMapper.blToEntity(persons)));
        }
        throw new UserFoundException(tmp_email);

    public void change_password(Integer userid, String newP, String oldP) {
        usersRepo.change_password(userid, newP, oldP);
    }

    public void profile_settings(UsersBL user, Integer userid) {
        usersRepo.profile_settings(userid, user.getUsername(), user.getFirstname(), user.getLastname(), user.getEmail());
    }

    public void deleteUser(Integer id) {
        usersRepo.deleteByUserid(id);
    }

    public UsersBL getUserByUserid(Integer id) {
        return usersMapper.entityToBl(usersRepo.getByUserid(id));
    }

}
