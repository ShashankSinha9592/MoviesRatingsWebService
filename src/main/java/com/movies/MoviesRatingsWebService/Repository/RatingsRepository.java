package com.movies.MoviesRatingsWebService.Repository;

import com.movies.MoviesRatingsWebService.DTO.MoviesDTO;
import com.movies.MoviesRatingsWebService.DTO.MoviesRatingsDTO;
import com.movies.MoviesRatingsWebService.Model.Ratings;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingsRepository extends JpaRepository<Ratings, String> {

    /**
     * @param rating : (Float) Average Rating
     * @param sort : it takes sort class that holds 2 values : field on which data to sort and direction on which direction to sort either in ascending order or descending order
     * @return : List of movies in sorted order according to field and direction greater than ratings
     */
    @Query("SELECT new com.movies.MoviesRatingsWebService.DTO.MoviesRatingsDTO(m.tConst, m.primaryTitle, m.runtimeMinutes, m.genres, r.averageRating) FROM Ratings r" +
            " INNER JOIN r.movie m" +
            " WHERE r.averageRating > :rating")
    public List<MoviesRatingsDTO> getTopRatedMovieInSortedOrderByField(@Param("rating") Float rating, Sort sort);

}
