package com.qwerty.practice.service;

import com.qwerty.practice.blmodel.DirectorBL;
import com.qwerty.practice.mapper.DirectorMapper;
import com.qwerty.practice.repository.DirectorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;


@Service

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost"}, allowCredentials = "true")
public class DirectorService {
    @Autowired
    DirectorMapper directorMapper;
    @Autowired
    DirectorRepo directorRepo;

    public Object getPopular() {
        return directorRepo.getPopular();
    }

    public DirectorBL getDirector(Integer movieid) {
        return directorMapper.entityToBl(directorRepo.getDirectorByMovieid(movieid));
    }
}
