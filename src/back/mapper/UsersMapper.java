package com.qwerty.practice.mapper;

import com.qwerty.practice.blmodel.UsersBL;
import com.qwerty.practice.dto.UsersDTO;
import com.qwerty.practice.dto.UsersPostDTO;
import com.qwerty.practice.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsersMapper {
    UsersMapper INSTANCE = Mappers.getMapper(UsersMapper.class);

    @Mapping(target = "userid", ignore = true)
    Users blToEntity(UsersBL usersBL);

    UsersBL entityToBl(Users user);

    List<UsersBL> entitiesToBls(List<Users> usersList);

    @Mapping(target = "userid", ignore = true)
    UsersBL dtoToBl(UsersPostDTO usersDTO);


    UsersDTO blToDto(UsersBL usersBL);

    List<UsersDTO> blsToDtos(List<UsersBL> usersBLList);
}
