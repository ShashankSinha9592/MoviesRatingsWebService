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

    @Query("SELECT new com.movies.MoviesRatingsWebService.DTO.MoviesRatingsDTO(m.tConst, m.primaryTitle, m.runtimeMinutes, m.genres, r.averageRating) FROM Ratings r" +
            " INNER JOIN r.movie m" +
            " WHERE r.averageRating > :rating")
    public List<MoviesRatingsDTO> getTopRatedMovieInSortedOrderByField(@Param("rating") Float rating, Sort sort);

}
