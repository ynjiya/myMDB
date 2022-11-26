package com.qwerty.practice.service;

import com.qwerty.practice.blmodel.MovieBL;
import com.qwerty.practice.controller.MovieController;
import com.qwerty.practice.entity.Movie;
import com.qwerty.practice.mapper.MovieMapper;
import com.qwerty.practice.repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;


@Service

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost"}, allowCredentials = "true")

public class MovieService {
    @Autowired
    MovieMapper movieMapper;
    @Autowired
    MovieRepo movieRepo;

    public List<MovieBL> getAllMovie(Boolean upcoming, Boolean toprated, Integer page, Integer genreid, MovieController.Order order, MovieController.Sort sort) {
        boolean sort_asc = false;
        boolean sort_desc = true;
        if (sort == MovieController.Sort.asc){
            sort_asc = true;
            sort_desc = false;
        }

        if (order == MovieController.Order.date) {
            return movieMapper.entitiesToBls(movieRepo.findAllOrder(upcoming, toprated, page, genreid, false, true, false, false, sort_asc, sort_desc));
        }

        if (order == MovieController.Order.popularity) {
            return movieMapper.entitiesToBls(movieRepo.findAllOrder(upcoming, toprated, page, genreid, false, false, true, false, sort_asc, sort_desc));
        }

        if (order == MovieController.Order.runtime) {
            return movieMapper.entitiesToBls(movieRepo.findAllOrder(upcoming, toprated, page, genreid, false, false, false, true, sort_asc, sort_desc));
        }
        return movieMapper.entitiesToBls(movieRepo.findAllOrder(upcoming, toprated, page, genreid, true, false, false, false, sort_asc, sort_desc));
    }

    public MovieBL getOne(Integer movieid) {
        return movieMapper.entityToBl(movieRepo.getMovieByMovieid(movieid));
    }

    public List<MovieBL> getToprated(Integer page) {
        return movieMapper.entitiesToBls(movieRepo.getToprated(page));
    }

    public List<MovieBL> getPopular(Integer page) {
        return movieMapper.entitiesToBls(movieRepo.getPopular(page));
    }

    public List<MovieBL> getUpcoming(Integer page) {
        return movieMapper.entitiesToBls(movieRepo.getUpcoming(page));
    }

    public List<MovieBL> getByUserid(Integer userid, Integer genreid, MovieController.Order order, MovieController.Sort sort) {
        if (order != null && sort != null)  {
            if (order == MovieController.Order.vote && sort == MovieController.Sort.asc) {
                return movieMapper.entitiesToBls(movieRepo.getMovieByUseridOrder(userid, genreid, true, false, false, false, true, false));
            }
            if (order == MovieController.Order.vote && sort == MovieController.Sort.desc) {
                return movieMapper.entitiesToBls(movieRepo.getMovieByUseridOrder(userid, genreid, true, false, false, false, false, true));
            }

            if (order == MovieController.Order.date && sort == MovieController.Sort.asc) {
                return movieMapper.entitiesToBls(movieRepo.getMovieByUseridOrder(userid, genreid, false, true, false, false, true, false));
            }
            if (order == MovieController.Order.date && sort == MovieController.Sort.desc) {
                return movieMapper.entitiesToBls(movieRepo.getMovieByUseridOrder(userid, genreid, false, true, false, false, false, true));
            }

            if (order == MovieController.Order.popularity && sort == MovieController.Sort.asc) {
                return movieMapper.entitiesToBls(movieRepo.getMovieByUseridOrder(userid, genreid, false, false, true, false, true, false));
            }
            if (order == MovieController.Order.popularity && sort == MovieController.Sort.desc) {
                return movieMapper.entitiesToBls(movieRepo.getMovieByUseridOrder(userid, genreid, false, false, true, false, false, true));
            }

            if (order == MovieController.Order.runtime && sort == MovieController.Sort.asc) {
                return movieMapper.entitiesToBls(movieRepo.getMovieByUseridOrder(userid, genreid, false, false, false, true, true, false));
            }
            if (order == MovieController.Order.runtime && sort == MovieController.Sort.desc) {
                return movieMapper.entitiesToBls(movieRepo.getMovieByUseridOrder(userid, genreid, false, false, false, true, false, true));
            }
        }
        return movieMapper.entitiesToBls(movieRepo.getMovieByUserid(userid, genreid));
    }

    public List<MovieBL> searchByTitle(String str, Boolean upcoming, Boolean toprated, Boolean watchlist, Integer wlid, Integer genreid, MovieController.Order order, MovieController.Sort sort) {
        if (order != null && sort != null)  {
            if (order == MovieController.Order.vote && sort == MovieController.Sort.asc) {
                return movieMapper.entitiesToBls(movieRepo.searchByTitleOrder(str, upcoming, toprated, watchlist, wlid, genreid, true, false, false, false, true, false));
            }
            if (order == MovieController.Order.vote && sort == MovieController.Sort.desc) {
                return movieMapper.entitiesToBls(movieRepo.searchByTitleOrder(str, upcoming, toprated, watchlist, wlid, genreid, true, false, false, false, false, true));
            }

            if (order == MovieController.Order.date && sort == MovieController.Sort.asc) {
                return movieMapper.entitiesToBls(movieRepo.searchByTitleOrder(str, upcoming, toprated, watchlist, wlid, genreid, false, true, false, false, true, false));
            }
            if (order == MovieController.Order.date && sort == MovieController.Sort.desc) {
                return movieMapper.entitiesToBls(movieRepo.searchByTitleOrder(str, upcoming, toprated, watchlist, wlid, genreid, false, true, false, false, false, true));
            }

            if (order == MovieController.Order.popularity && sort == MovieController.Sort.asc) {
                return movieMapper.entitiesToBls(movieRepo.searchByTitleOrder(str, upcoming, toprated, watchlist, wlid, genreid, false, false, true, false, true, false));
            }
            if (order == MovieController.Order.popularity && sort == MovieController.Sort.desc) {
                return movieMapper.entitiesToBls(movieRepo.searchByTitleOrder(str, upcoming, toprated, watchlist, wlid, genreid, false, false, true, false, false, true));
            }

            if (order == MovieController.Order.runtime && sort == MovieController.Sort.asc) {
                return movieMapper.entitiesToBls(movieRepo.searchByTitleOrder(str, upcoming, toprated, watchlist, wlid, genreid, false, false, false, true, true, false));
            }
            if (order == MovieController.Order.runtime && sort == MovieController.Sort.desc) {
                return movieMapper.entitiesToBls(movieRepo.searchByTitleOrder(str, upcoming, toprated, watchlist, wlid, genreid, false, false, false, true, false, true));
            }
        }
        return movieMapper.entitiesToBls(movieRepo.searchByTitle(str, genreid));
    }


    public List<MovieBL> searchByDirector(String str, Boolean upcoming, Boolean toprated, Boolean watchlist, Integer wlid, Integer genreid, MovieController.Order order, MovieController.Sort sort) {
        if (order != null && sort != null)  {
            if (order == MovieController.Order.vote && sort == MovieController.Sort.asc) {
                return movieMapper.entitiesToBls(movieRepo.searchByDirectorOrder(str, upcoming, toprated, watchlist, wlid, genreid, true, false, false, false, true, false));
            }
            if (order == MovieController.Order.vote && sort == MovieController.Sort.desc) {
                return movieMapper.entitiesToBls(movieRepo.searchByDirectorOrder(str, upcoming, toprated, watchlist, wlid, genreid, true, false, false, false, false, true));
            }

            if (order == MovieController.Order.date && sort == MovieController.Sort.asc) {
                return movieMapper.entitiesToBls(movieRepo.searchByDirectorOrder(str, upcoming, toprated, watchlist, wlid, genreid, false, true, false, false, true, false));
            }
            if (order == MovieController.Order.date && sort == MovieController.Sort.desc) {
                return movieMapper.entitiesToBls(movieRepo.searchByDirectorOrder(str, upcoming, toprated, watchlist, wlid, genreid, false, true, false, false, false, true));
            }

            if (order == MovieController.Order.popularity && sort == MovieController.Sort.asc) {
                return movieMapper.entitiesToBls(movieRepo.searchByDirectorOrder(str, upcoming, toprated, watchlist, wlid, genreid, false, false, true, false, true, false));
            }
            if (order == MovieController.Order.popularity && sort == MovieController.Sort.desc) {
                return movieMapper.entitiesToBls(movieRepo.searchByDirectorOrder(str, upcoming, toprated, watchlist, wlid, genreid, false, false, true, false, false, true));
            }

            if (order == MovieController.Order.runtime && sort == MovieController.Sort.asc) {
                return movieMapper.entitiesToBls(movieRepo.searchByDirectorOrder(str, upcoming, toprated, watchlist, wlid, genreid, false, false, false, true, true, false));
            }
            if (order == MovieController.Order.runtime && sort == MovieController.Sort.desc) {
                return movieMapper.entitiesToBls(movieRepo.searchByDirectorOrder(str, upcoming, toprated, watchlist, wlid, genreid, false, false, false, true, false, true));
            }
        }
        return movieMapper.entitiesToBls(movieRepo.searchByDirector(str, genreid));
    }
}
