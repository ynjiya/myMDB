package com.qwerty.practice.controller;

import com.qwerty.practice.dto.DirectorDTO;
import com.qwerty.practice.mapper.DirectorMapper;
import com.qwerty.practice.service.DirectorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api")

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost"}, allowCredentials = "true")
public class DirectorController {
    private static final Logger logger = LoggerFactory.getLogger(DirectorController.class);

    @Autowired
    DirectorMapper directorMapper;

    @Autowired
    DirectorService directorService;

//    @GetMapping(value = "/director/popular")
//    public ResponseEntity getPopular() {
//        logger.info("GET /director/popular from getPopular()");
//        return ResponseEntity.ok(directorService.getPopular());
//    }

//    @GetMapping(value = "/directors/{movieid}")
//    public ResponseEntity<DirectorDTO> getDirector(@PathVariable Integer movieid) {
//        logger.info("GET /directors/{} from getDirector()", movieid);
//        return new ResponseEntity<>(directorMapper.blToDto(directorService.getDirector(movieid)), HttpStatus.OK);
//    }
}
