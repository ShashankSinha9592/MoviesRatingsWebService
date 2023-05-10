package com.movies.MoviesRatingsWebService.Controller;

import com.movies.MoviesRatingsWebService.DTO.MoviesDTO;
import com.movies.MoviesRatingsWebService.Service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    @Autowired
    MoviesService moviesService;

    @PostMapping
    public ResponseEntity<List<MoviesDTO>> saveMoviesFormCsvFile(){

        List<MoviesDTO> moviesDTOList = moviesService.addCsvFileMovies();

        return new ResponseEntity<>(moviesDTOList, HttpStatus.CREATED);

    }
    @GetMapping
    public ResponseEntity<List<MoviesDTO>> getLongestDurationMovies(){

        List<MoviesDTO> longestDurationMovies = moviesService.getLongestDurationMovies();

        return new ResponseEntity<>(longestDurationMovies, HttpStatus.OK);

    }


}
