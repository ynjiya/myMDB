package com.qwerty.practice.service;

import com.qwerty.practice.blmodel.ReviewBL;
import com.qwerty.practice.mapper.ReviewMapper;
import com.qwerty.practice.repository.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;


@Service

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost"}, allowCredentials = "true")
public class ReviewsService {

    @Autowired
    ReviewMapper reviewMapper;
    @Autowired
    ReviewRepo reviewRepo;

    public Object getReviewsJson(Integer movieid) {
        return reviewRepo.getReviewByMovieidJson(movieid);
    }

    public ReviewBL addReview(ReviewBL review) {
        return reviewMapper.entityToBl(reviewRepo.save(reviewMapper.blToEntity(review)));
    }

    public void deleteReviews(Integer id) {
        reviewRepo.deleteByReviewid(id);
    }
}
