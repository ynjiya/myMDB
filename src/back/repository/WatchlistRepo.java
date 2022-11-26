package com.qwerty.practice.repository;

import com.qwerty.practice.entity.Movie;
import com.qwerty.practice.entity.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface WatchlistRepo  extends JpaRepository<Watchlist, Long> {
    Watchlist findByMovieMovieidAndUsersUserid(Integer movieId, Integer userId);

    @Transactional
    @Modifying
    @Query(value = """
            DELETE FROM watchlist
            WHERE userid = ?1 and movieid = ?2 ;""",
            nativeQuery = true)
    void deleteByMovieidAndUserid(Integer userid, Integer movieid);
}
