package com.movies.MoviesRatingsWebService.Controller;

import com.movies.MoviesRatingsWebService.DTO.MoviesDTO;
import com.movies.MoviesRatingsWebService.Service.MoviesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class MoviesControllerTest {

    @Autowired
    MoviesController moviesController;
    @Test
    void testAddMovie() {

        MoviesDTO moviesDTO = new MoviesDTO();

        moviesDTO.setGenres("  ");
        moviesDTO.setTConst("1234td");
        moviesDTO.setPrimaryTitle("asasass");
        moviesDTO.setTitleType("sdsfsd");
        moviesDTO.setRuntimeMinutes(1);

        Assertions.assertThrows(Exception.class,()-> moviesController.addMovie(moviesDTO));

    }
}