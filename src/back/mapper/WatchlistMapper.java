package com.qwerty.practice.mapper;

import com.qwerty.practice.blmodel.WatchlistBL;
import com.qwerty.practice.dto.WatchlistDTO;
import com.qwerty.practice.dto.WatchlistPostDTO;
import com.qwerty.practice.entity.Movie;
import com.qwerty.practice.entity.Users;
import com.qwerty.practice.entity.Watchlist;
import com.qwerty.practice.repository.MovieRepo;
import com.qwerty.practice.repository.UsersRepo;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WatchlistMapper {
    WatchlistMapper INSTANCE = Mappers.getMapper(WatchlistMapper.class);
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "movie", ignore = true)
    @Mapping(target = "wlid", ignore = true)
    Watchlist blToEntity(WatchlistBL watchlistBL, @Context MovieRepo movieRepo, @Context UsersRepo usersRepo);

    @AfterMapping
    default void afterBlToEntity(WatchlistBL watchlistBL, @MappingTarget Watchlist watchlist, @Context MovieRepo movieRepo, @Context UsersRepo usersRepo) {
        if (watchlistBL.getMovieid() != null &&
                (watchlist.getMovie() == null ||
                        !watchlist.getMovie().getMovieid().equals(watchlistBL.getMovieid()))) {
            if (movieRepo.getMovieByMovieid(watchlistBL.getMovieid()) == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found");
            }

            final Movie movie = new Movie();
            movie.setMovieid(watchlistBL.getMovieid());
            watchlist.setMovie(movie);
        }

        if (watchlistBL.getUsersid() != null &&
                (watchlist.getUsers() == null ||
                        !watchlist.getUsers().getUserid().equals(watchlistBL.getUsersid()))) {
            if (usersRepo.getByUserid(watchlistBL.getUsersid()) == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
            }

            final Users user = new Users();
            user.setUserid(watchlistBL.getUsersid());
            watchlist.setUsers(user);

        }
    }

    @Mapping(target = "usersid", ignore = true)
    @Mapping(target = "movieid", ignore = true)
    @InheritInverseConfiguration
    WatchlistBL entityToBl(Watchlist watchlist);

    @AfterMapping
    default void afterEntityToBl(Watchlist watchlist, @MappingTarget WatchlistBL watchlistBL) {
        watchlistBL.setMovieid(watchlist.getMovie().getMovieid());
        watchlistBL.setUsersid(watchlist.getUsers().getUserid());
    }

    List<WatchlistBL> entitiesToBLs(List<Watchlist> watchlist);
    WatchlistDTO blToDto(WatchlistBL watchlistBL);

    List<WatchlistDTO> blsToDtos(List<WatchlistBL> watchlist);
    @Mapping(target = "wlid", ignore = true)
    WatchlistBL dtoToBl(WatchlistPostDTO watchlistDTO);
}
