package com.movies.MoviesRatingsWebService.Repository;

import com.movies.MoviesRatingsWebService.DTO.GenreMovie;
import com.movies.MoviesRatingsWebService.DTO.MoviesDTO;
import com.movies.MoviesRatingsWebService.DTO.MoviesRatingsDTO;
import com.movies.MoviesRatingsWebService.Model.Movies;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesRepository extends JpaRepository<Movies, String> {

    /**
     *
     * @return : Top 10 top Rated Movies
     */
    @Query("SELECT new com.movies.MoviesRatingsWebService.DTO.MoviesRatingsDTO(m.tConst, m.primaryTitle, m.runtimeMinutes, m.genres, r.averageRating) from Movies m " +
            "INNER JOIN m.rating r " +
            "ORDER BY m.runtimeMinutes DESC LIMIT 10")
    public List<MoviesRatingsDTO> getTop10LongestDurationMovies();


    /**
     * @param rating : (Float) Average Rating
     * @param sort : it takes sort class that holds 2 values : field on which data to sort and direction on which direction to sort either in ascending order or descending order
     * @return : List of movies in sorted order according to field and direction greater than ratings
     */
    @Query("SELECT new com.movies.MoviesRatingsWebService.DTO.MoviesRatingsDTO(m.tConst, m.primaryTitle, m.runtimeMinutes, m.genres, r.averageRating) FROM Movies m" +
            " INNER JOIN m.rating r" +
            " WHERE r.averageRating > :rating")
    public List<MoviesRatingsDTO> getTopRatedMovieInSortedOrderByField(@Param("rating") Float rating, Sort sort);


    /**
     * @return : A list of all movies genre-wise with Subtotals of their numVotes.
     */
    @Query("SELECT new com.movies.MoviesRatingsWebService.DTO.GenreMovie(m.genres, COUNT(m) AS moviesCount, SUM(r.numVotes) AS subTotalNumVotes) " +
            "FROM Movies m INNER JOIN m.rating r " +
            "GROUP BY m.genres")
    public List<GenreMovie> getAllMoviesByGenreAndTotalNumCount();



    /**
     * @return : (Integer) Number of rows updated
     * @Behaviour : Increment runtimeMinutes by :
     * 15 if genre = Documentary
     * 30 if genre = Animation
     * 45 for the rest
     */
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE Movies SET runtime_minutes = " +
            "CASE genres " +
            "WHEN 'Documentary' THEN runtime_minutes + 15 " +
            "WHEN 'Animation' THEN runtime_minutes + 30 " +
            "ELSE runtime_minutes + 45 " +
            "END", nativeQuery = true)
    @Transactional
    public Integer updateRuntimeMinutes();


}
