package com.movies.MoviesRatingsWebService.Repository;

import com.movies.MoviesRatingsWebService.DTO.GenreMovie;
import com.movies.MoviesRatingsWebService.DTO.MoviesDTO;
import com.movies.MoviesRatingsWebService.Model.Movies;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesRepository extends JpaRepository<Movies, String> {

    @Query("SELECT new com.movies.MoviesRatingsWebService.DTO.MoviesDTO(m.tConst, m.titleType, m.primaryTitle, m.runtimeMinutes, m.genres) from Movies m" +
            " ORDER BY m.runtimeMinutes DESC LIMIT 10")
    public List<MoviesDTO> getTop10LongestDurationMovies();


    @Query("SELECT new com.movies.MoviesRatingsWebService.DTO.MoviesDTO(m.tConst, m.titleType, m.primaryTitle, m.runtimeMinutes, m.genres, r.averageRating, r.numVotes) FROM Movies m" +
            " INNER JOIN m.rating r" +
            " WHERE r.averageRating > :rating")
    public List<MoviesDTO> getTopRatedMovieInSortedOrderByField(@Param("rating") Float rating, Sort sort);


    @Query("SELECT new com.movies.MoviesRatingsWebService.DTO.GenreMovie(m.genres, COUNT(m) AS moviesCount, SUM(r.numVotes) AS subTotalNumVotes)" +
            " FROM Movies m INNER JOIN m.rating r " +
            "GROUP BY m.genres")
    public List<GenreMovie> getAllMoviesByGenreAndTotalNumCount();


}
