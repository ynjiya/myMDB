package com.qwerty.practice.mapper;

import com.qwerty.practice.blmodel.MovieBL;
import com.qwerty.practice.dto.MovieDTO;
import com.qwerty.practice.entity.Movie;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {DirectorMapper.class, GenreMapper.class}
)
public interface MovieMapper {
    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    MovieBL entityToBl(Movie movie);
    List<MovieBL> entitiesToBls(List<Movie> movie);

    MovieDTO blToDto(MovieBL movieBL);
    List<MovieDTO> blsToDtos(List<MovieBL> movieBL);

}
