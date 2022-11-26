package com.qwerty.practice.repository;

import com.qwerty.practice.entity.Genre;
import com.qwerty.practice.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepo extends JpaRepository<Genre, Long> {

//    @Query(value = """
//            SELECT g.genreid, g.genrename
//            FROM genre g join movie_genre mg on g.genreid = mg.genreid
//            WHERE movieid = ?1 ;""",
//            nativeQuery = true)
//    List<Genre> findAllByMovieListMovieid(Integer movieid);
}
