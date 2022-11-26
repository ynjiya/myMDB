package com.qwerty.practice.controller;
import com.qwerty.practice.dto.MovieDTO;
import com.qwerty.practice.exception.NotFoundMovieException;
import com.qwerty.practice.mapper.MovieMapper;
import com.qwerty.practice.service.MovieService;
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

public class MovieController {
    public enum Filter {
        all, upcoming, toprated, watchlist;
    }

    public enum Search {
        title, director;
    }

    public enum Order {
        vote, date, popularity, runtime;
    }

    public enum Sort {
        asc, desc;
    }

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    MovieMapper movieMapper;

    @Autowired
    MovieService movieService;

    @GetMapping(value = "/movies/{movieid}")
    public ResponseEntity<MovieDTO> getOne(@PathVariable Integer movieid) throws NotFoundMovieException {
        logger.info("GET /movies/{} from getOne()", movieid);
        return new ResponseEntity<>(movieMapper.blToDto(movieService.getOne(movieid)), HttpStatus.OK);
    }

    @GetMapping(value = {"/movies"})
    public ResponseEntity<List<MovieDTO>> getAllMovie(@RequestParam(defaultValue = "all") Filter list,
                                                      @RequestParam(required = false) Search search,
                                                      @RequestParam(defaultValue = "0") Integer wlid,
                                                      @RequestParam(required = false) String searchstring,
                                                      @RequestParam(defaultValue = "1") Integer page,
                                                      @RequestParam(defaultValue = "0") Integer genreid,
                                                      @RequestParam(defaultValue = "vote") Order order,
                                                      @RequestParam(defaultValue = "desc") Sort sort) {
        logger.info("GET /movies?list={}&page={}&genre={}&order={}&sort={}", list, page, genreid, order, sort);


        boolean upcoming = false;
        boolean toprated = false;
        boolean watchlist = false;

        if (list == Filter.upcoming) {
            upcoming = true;
        }

        if (list == Filter.toprated) {
            toprated = true;

        }

        if (list == Filter.watchlist) {
            watchlist = true;
        }

        if (search == Search.director) {
            if (searchstring != null) {
                logger.info("GET /movies?list={}&searchstr={}&page={}&genre={}&order={}&sort={}", list, searchstring, page, genreid, order, sort);

                return new ResponseEntity<>(movieMapper.blsToDtos(movieService.searchByDirector(searchstring, upcoming, toprated, watchlist, wlid, genreid, order, sort)), HttpStatus.OK);
            }
            else { return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}
        }

        if (search == Search.title) {
            if (searchstring != null) {
                logger.info("GET /movies?list={}&searchstr={}&page={}&genre={}&order={}&sort={}", list, searchstring, page, genreid, order, sort);

                return new ResponseEntity<>(movieMapper.blsToDtos(movieService.searchByTitle(searchstring, upcoming, toprated, watchlist, wlid, genreid, order, sort)), HttpStatus.OK);
            }
            else { return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}
        }

        if (list == Filter.watchlist) {
            if (wlid != null) {
                logger.info("GET /movies?list={}&wlid={}&page={}&genre={}&order={}&sort={}", list, wlid, page, genreid, order, sort);

                return new ResponseEntity<>(movieMapper.blsToDtos(movieService.getByUserid(wlid, genreid, order, sort)), HttpStatus.OK);
            }
            else { return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}
        }


        logger.info("GET /movies?list={}&page={}&genre={}&order={}&sort={}", list, page, genreid, order, sort);
        return new ResponseEntity<>(movieMapper.blsToDtos(movieService.getAllMovie(upcoming, toprated, page, genreid, order, sort)), HttpStatus.OK);
    }
}
