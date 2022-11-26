package com.qwerty.practice.mapper;

import com.qwerty.practice.blmodel.DirectorBL;
import com.qwerty.practice.dto.DirectorDTO;
import com.qwerty.practice.entity.Director;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DirectorMapper {
    DirectorMapper INSTANCE = Mappers.getMapper(DirectorMapper.class);

    DirectorDTO blToDto(DirectorBL directorBL);
    DirectorBL entityToBl(Director director);
}
