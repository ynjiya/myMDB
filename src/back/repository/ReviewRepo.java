package com.qwerty.practice.repository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.qwerty.practice.entity.Movie;
import com.qwerty.practice.entity.Review;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {

    @Query(value = "SELECT * FROM movie_rc(:movie_id);",  nativeQuery = true)
    Object getReviewByMovieidJson(@Param("movie_id") Integer movieid);

    void deleteByReviewid(Integer reviewid);
}
