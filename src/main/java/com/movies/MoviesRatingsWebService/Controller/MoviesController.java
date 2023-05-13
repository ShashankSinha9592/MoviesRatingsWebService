package com.movies.MoviesRatingsWebService.Controller;

import com.movies.MoviesRatingsWebService.DTO.GenreMovie;
import com.movies.MoviesRatingsWebService.DTO.MoviesDTO;
import com.movies.MoviesRatingsWebService.DTO.MoviesRatingsDTO;
import com.movies.MoviesRatingsWebService.Model.Direction;
import com.movies.MoviesRatingsWebService.Model.Field;
import com.movies.MoviesRatingsWebService.Service.MoviesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MoviesController {

    @Autowired
    MoviesService moviesService;

    @GetMapping("/longest-duration-movie")
    public ResponseEntity<List<MoviesRatingsDTO>> getLongestDurationMovies(){

        List<MoviesRatingsDTO> longestDurationMovies = moviesService.getLongestDurationMovies();

        return new ResponseEntity<>(longestDurationMovies, HttpStatus.OK);

    }
    @PostMapping("/new-movie")
    public ResponseEntity<String> addMovie(@Valid @RequestBody MoviesDTO moviesDTO){

        String added = moviesService.addNewMovie(moviesDTO);

        return new ResponseEntity<>(added, HttpStatus.CREATED);

    }

    @GetMapping("/top-rated-movies/{field}/{rating}/{direction}")
    public ResponseEntity<List<MoviesRatingsDTO>> getTopRatedMovies(@PathVariable Field field, @PathVariable Float rating, @PathVariable Direction direction){

        List<MoviesRatingsDTO> topRatedMovies = moviesService.getTopRatedMovieByMovieField(field,rating,direction);

        return new ResponseEntity<>(topRatedMovies, HttpStatus.OK);

    }

    @GetMapping("/top-rated-movies")
    public ResponseEntity<List<MoviesRatingsDTO>> getTopRatedMovies(){

        Field field = Field.averageRating;
        Float rating = 6f;
        Direction direction = Direction.ASC;

        List<MoviesRatingsDTO> topRatedMovies = moviesService.getTopRatedMovieByMovieField(field,rating,direction);

        return new ResponseEntity<>(topRatedMovies, HttpStatus.OK);

    }

    @GetMapping("/genre-movies-with-subtotals")
    public ResponseEntity<List<GenreMovie>> getGenreMoviesByTotal(){

        List<GenreMovie> movies = moviesService.genreMoviesBySubTotal();

        return new ResponseEntity<>(movies, HttpStatus.OK);

    }

    @GetMapping("/update-runtime-minutes")
    public ResponseEntity<String> updateRuntimeMinutes(){

        Integer numOfRowsUpdated = moviesService.updateRunTimeMinutes();

        String response = "Number of rows updated : "+numOfRowsUpdated;

        return new ResponseEntity<>(response, HttpStatus.OK);

    }



}
