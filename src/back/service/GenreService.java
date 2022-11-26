package com.qwerty.practice.service;


import com.qwerty.practice.blmodel.GenreBL;
import com.qwerty.practice.entity.Genre;
import com.qwerty.practice.mapper.GenreMapper;
import com.qwerty.practice.repository.GenreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost"}, allowCredentials = "true")

public class GenreService {
    @Autowired
    GenreMapper genreMapper;

    @Autowired
    GenreRepo genreRepo;
}
