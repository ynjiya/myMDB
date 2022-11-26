package com.qwerty.practice.mapper;

import com.qwerty.practice.blmodel.ReviewBL;
import com.qwerty.practice.dto.ReviewDTO;
import com.qwerty.practice.dto.ReviewPostDTO;
import com.qwerty.practice.entity.Movie;
import com.qwerty.practice.entity.Review;
import com.qwerty.practice.entity.Users;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    @Mapping(target = "users", ignore = true)
    @Mapping(target = "movie", ignore = true)
    @Mapping(target = "reviewid", ignore = true)
    Review blToEntity(ReviewBL reviewBL);

    @AfterMapping
    default void afterBlToEntity(ReviewBL reviewBL, @MappingTarget Review review) {
        if (reviewBL.getMovieid() != null) {
            final Movie movie = new Movie();
            movie.setMovieid(reviewBL.getMovieid());
            review.setMovie(movie);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found");

        if (reviewBL.getUsersid() != null) {
            final Users user = new Users();
            user.setUserid(reviewBL.getUsersid());
            review.setUsers(user);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }

    @Mapping(target = "usersid", ignore = true)
    @Mapping(target = "movieid", ignore = true)
    ReviewBL entityToBl(Review review);

    @AfterMapping
    default void afterEntityToBl(Review review, @MappingTarget ReviewBL reviewBL) {
        reviewBL.setMovieid(review.getMovie().getMovieid());
        reviewBL.setUsersid(review.getUsers().getUserid());
    }

    List<ReviewBL> entitiesToBls(List<Review> reviewList);

    ReviewDTO blToDto(ReviewBL reviewBL);
    List<ReviewDTO> blsToDtos(List<ReviewBL> reviewBLList);

    @Mapping(target = "reviewid", ignore = true)
    ReviewBL dtoToBl(ReviewPostDTO reviewDTO);

}
