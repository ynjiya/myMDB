package com.qwerty.practice.controller;

import com.qwerty.practice.dto.GenreDTO;
import com.qwerty.practice.mapper.GenreMapper;
import com.qwerty.practice.service.GenreService;
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
public class GenreController {
    private static final Logger logger = LoggerFactory.getLogger(GenreController.class);

    @Autowired
    GenreMapper genreMapper;
    @Autowired
    GenreService genreService;

//    @GetMapping(value = "/genres/{movieid}")
//    public ResponseEntity<List<GenreDTO>> getMovieGenres(@PathVariable Integer movieid) {
//        logger.info("GET /genres/{} from getMovieGenres()", movieid);
//        return new ResponseEntity<>(genreMapper.blsToDtos(genreService.getMovieGenres(movieid)), HttpStatus.OK);
//    }
}
