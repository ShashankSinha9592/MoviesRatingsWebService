package com.movies.MoviesRatingsWebService.Service;

import com.movies.MoviesRatingsWebService.DTO.GenreMovie;
import com.movies.MoviesRatingsWebService.DTO.MoviesRatingsDTO;
import com.movies.MoviesRatingsWebService.Model.Direction;
import com.movies.MoviesRatingsWebService.Model.Field;
import com.movies.MoviesRatingsWebService.DTO.MoviesDTO;

import java.util.List;

public interface MoviesService {

    /**
     * @Behaviour : This method add the default data from movies.csv file to the database
     * @return : List of movies
     */
    public List<MoviesDTO> addCsvFileMovies();


    public List<MoviesRatingsDTO> getLongestDurationMovies();

    /**
     * @param : moviesDto
     * @Behaviour : It saved the movie data into the database
     * @return : "Success"
     */
    public String addNewMovie(MoviesDTO moviesDto);

    /**
     * @param field     : sort the data according to field
     * @param rating    : get the data greater than rating;
     * @param direction : Direction to which it should be sorted aither in Ascending or Descending order
     * @return : List of movies in sorted order according to field and direction greater than ratings
     */
    List<MoviesRatingsDTO> getTopRatedMovieByMovieField(Field field, Float rating, Direction direction);

    /**
     * @return : A list of all movies genre-wise with Subtotals of their numVotes.
     */
    public List<GenreMovie> genreMoviesBySubTotal();

    /**
     * @return : (Integer) Number of rows updated
     * @Behaviour : Increment runtimeMinutes by :
     * 15 if genre = Documentary
     * 30 if genre = Animation
     * 45 for the rest
     */
    public Integer updateRunTimeMinutes();

}
