package com.qwerty.practice.repository;

import com.qwerty.practice.entity.Director;
import com.qwerty.practice.entity.Movie;
import com.qwerty.practice.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectorRepo extends JpaRepository<Director, Long> {

    @Query(value = """
            SELECT *
            FROM director
            ORDER BY popularity DESC;""",
            nativeQuery = true)
    List<Director> getPopular();

    @Query(value = """
            SELECT D.directorid, directorname, gender, D.popularity
            FROM director D JOIN movie M ON D.directorid = M.directorid
            WHERE movieid = ?1 ;""",
            nativeQuery = true)
    Director getDirectorByMovieid(Integer movieid);
}
