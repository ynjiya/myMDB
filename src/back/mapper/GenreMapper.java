package com.qwerty.practice.mapper;

import com.qwerty.practice.blmodel.GenreBL;
import com.qwerty.practice.dto.GenreDTO;
import com.qwerty.practice.entity.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);

    GenreBL entityToBl(Genre genre);
    List<GenreBL> entitiesToBls(List<Genre> genre);

    GenreDTO blToDto(GenreBL genreBL);
    List<GenreDTO> blsToDtos(List<GenreBL> genreBL);
}
