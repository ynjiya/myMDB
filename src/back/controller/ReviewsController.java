package com.qwerty.practice.controller;

import com.qwerty.practice.dto.ReviewDTO;
import com.qwerty.practice.dto.ReviewPostDTO;
import com.qwerty.practice.mapper.ReviewMapper;
import com.qwerty.practice.service.ReviewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api")

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost"}, allowCredentials = "true")
public class ReviewsController {
    private static final Logger logger = LoggerFactory.getLogger(ReviewsController.class);

    @Autowired
    ReviewMapper reviewMapper;
    @Autowired
    ReviewsService reviewsService;


    @GetMapping(value = "/reviews/{movieid}")
    public ResponseEntity<Object> getReviewsJson(@PathVariable Integer movieid) {
        logger.info("GET /reviews/{} from getReviewsJson()", movieid);
        return new ResponseEntity<>(reviewsService.getReviewsJson(movieid), HttpStatus.OK);
    }

    @PostMapping(value = "/reviews")
    public ResponseEntity<ReviewDTO> addReview(@RequestBody ReviewPostDTO review) {
        logger.info("POST /review from addReview() {}", review);
        return new ResponseEntity<>(reviewMapper.blToDto(reviewsService.addReview(reviewMapper.dtoToBl(review))), HttpStatus.OK);
    }

    @DeleteMapping(value = "/reviews/{reviewid}")
    public void deleteReviews(@PathVariable Integer reviewid) {
        logger.info("DELETE /reviews/{} from deleteReviews()", reviewid);
        reviewsService.deleteReviews(reviewid);
    }
}
