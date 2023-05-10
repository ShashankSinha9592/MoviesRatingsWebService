package com.movies.MoviesRatingsWebService.Service;

import com.movies.MoviesRatingsWebService.DTO.MoviesDTO;
import com.movies.MoviesRatingsWebService.DTO.RatingsDTO;
import com.movies.MoviesRatingsWebService.Model.Direction;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface RatingsService {

    /**
     * @Behaviour : Adds Ratings to the database from ratings.csv file
     * @return : List of Ratings
     */
    public List<RatingsDTO> addRatingsFromCsvFile();


    /**
     *
     * @param movieId : Unique Id of a Movie
     * @param ratingsDTO
     * @return : "Success"
     */
    public String giveAvgRatingToMovie(String movieId, RatingsDTO ratingsDTO);

    /**
     *
     * @param field : sort the data according to field
     * @param sort : it takes sort class that holds 2 values : field on which data to sort and direction on which direction to sort either in ascending order or descending order
     * @return : List of movies in sorted order according to field and direction greater than ratings
     */
    List<MoviesDTO> getTopRatedMovieByRatingField(Float rating, Sort sort);

}
