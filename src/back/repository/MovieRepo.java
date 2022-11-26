package com.qwerty.practice.repository;
import com.qwerty.practice.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Long> {

    @Query(value = "select * from all_movies(:page, :genreid) ;", nativeQuery = true)
    List<Movie> findAll(@Param("page") Integer page,
                        @Param("genreid") Integer genreid);

    @Query(value = "select * from all_movies(:upcoming, :toprated, :page, :genreid, :by_vote, :by_date, :by_popularity, :by_runtime, :sort_asc, :sort_desc) ;", nativeQuery = true)
    List<Movie> findAllOrder(@Param("upcoming") Boolean upcoming,
                                  @Param("toprated") Boolean toprated,
                                  @Param("page") Integer page,
                                  @Param("genreid") Integer genreid,
                                  @Param("by_vote") Boolean by_vote,
                                  @Param("by_date") Boolean by_date,
                                  @Param("by_popularity") Boolean by_popularity,
                                  @Param("by_runtime") Boolean by_runtime,
                                  @Param("sort_asc") Boolean sort_asc,
                                  @Param("sort_desc") Boolean sort_desc);


    @Query(value = "SELECT * FROM MOVIE WHERE movieid = ?1",
            nativeQuery = true)
    Movie getMovieByMovieid(Integer movieid);


    @Query(value = """
            SELECT * FROM movie
            WHERE vote_average >= 8
            ORDER BY vote_average DESC 
            LIMIT 20 OFFSET (?1 - 1) * 20;""",
            nativeQuery = true)
    List<Movie> getToprated(Integer page);

    @Query(value = """
            SELECT *
            FROM movie
            ORDER BY popularity DESC 
            LIMIT 20 OFFSET (?1 - 1) * 20;""",
            nativeQuery = true)
    List<Movie> getPopular(Integer page);

    @Query(value = """
            SELECT * FROM movie
            WHERE date_part('year', release_date) >= date_part('year', current_date)
                AND date_part('month', release_date) >= date_part('month', current_date) - 1
            ORDER BY popularity DESC 
            LIMIT 20 OFFSET (?1 - 1) * 20;""",
            nativeQuery = true)
    List<Movie> getUpcoming(Integer page);

    @Query(value = "select * from users_watchlist(:userid, :genreid) ;", nativeQuery = true)
    List<Movie> getMovieByUserid(@Param("userid") Integer userid,
                                 @Param("genreid") Integer genreid);

    @Query(value = "select * from users_watchlist(:userid, :genreid, :by_vote, :by_date, :by_popularity, :by_runtime, :sort_asc, :sort_desc) ;", nativeQuery = true)
    List<Movie> getMovieByUseridOrder(@Param("userid") Integer userid,
                                      @Param("genreid") Integer genreid,
                                      @Param("by_vote") Boolean by_vote,
                                      @Param("by_date") Boolean by_date,
                                      @Param("by_popularity") Boolean by_popularity,
                                      @Param("by_runtime") Boolean by_runtime,
                                      @Param("sort_asc") Boolean sort_asc,
                                      @Param("sort_desc") Boolean sort_desc);


    @Query(value = "SELECT * FROM title_search(:movie_title, :upcoming, :toprated, :watchlist, :wlid, :genreid, :by_vote, :by_date, :by_popularity, :by_runtime, :sort_asc, :sort_desc) ;",  nativeQuery = true)
    List<Movie> searchByTitleOrder(@Param("movie_title") String movie_title,
                                   @Param("upcoming") Boolean upcoming,
                                   @Param("toprated") Boolean toprated,
                                   @Param("watchlist") Boolean watchlist,
                                   @Param("wlid") Integer wlid,
                                   @Param("genreid") Integer genreid,
                                   @Param("by_vote") Boolean by_vote,
                                   @Param("by_date") Boolean by_date,
                                   @Param("by_popularity") Boolean by_popularity,
                                   @Param("by_runtime") Boolean by_runtime,
                                   @Param("sort_asc") Boolean sort_asc,
                                   @Param("sort_desc") Boolean sort_desc);

    @Query(value = "SELECT * FROM title_search(:movie_title, :genreid);",  nativeQuery = true)
    List<Movie> searchByTitle(@Param("movie_title") String movie_title,
                              @Param("genreid") Integer genreid);


    @Query(value = "SELECT * FROM director_search(:dir_name, :upcoming, :toprated, :watchlist, :wlid, :genreid, :by_vote, :by_date, :by_popularity, :by_runtime, :sort_asc, :sort_desc) ;",  nativeQuery = true)
    List<Movie> searchByDirectorOrder(@Param("dir_name") String dir_name,
                                      @Param("upcoming") Boolean upcoming,
                                      @Param("toprated") Boolean toprated,
                                      @Param("watchlist") Boolean watchlist,
                                      @Param("wlid") Integer wlid,
                                      @Param("genreid") Integer genreid,
                                       @Param("by_vote") Boolean by_vote,
                                       @Param("by_date") Boolean by_date,
                                       @Param("by_popularity") Boolean by_popularity,
                                       @Param("by_runtime") Boolean by_runtime,
                                       @Param("sort_asc") Boolean sort_asc,
                                       @Param("sort_desc") Boolean sort_desc);

    @Query(value = "SELECT * FROM director_search(:dir_name, :genreid);",  nativeQuery = true)
    List<Movie> searchByDirector(@Param("dir_name") String dir_name,
                                 @Param("genreid") Integer genreid);
}
