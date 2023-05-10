package com.movies.MoviesRatingsWebService.Service;

import com.movies.MoviesRatingsWebService.DTO.GenreMovie;
import com.movies.MoviesRatingsWebService.DTO.MoviesDTO;
import com.movies.MoviesRatingsWebService.Model.Direction;
import com.movies.MoviesRatingsWebService.Model.Field;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MoviesServiceImplementationTest {

    @Autowired
    MoviesService moviesService;

    @Test
    void addCsvFileMovies() {

        List<MoviesDTO> moviesDTOList = moviesService.addCsvFileMovies();
        assertTrue(!moviesDTOList.isEmpty());

    }

    @Test
    void getLongestDurationMovies() {

        List<MoviesDTO> moviesDTOList = moviesService.getLongestDurationMovies();

        Assertions.assertTrue(10==moviesDTOList.size());

    }

    @Test
    void getTopRatedMovie() {

        List<MoviesDTO> moviesDTOList = moviesService.getTopRatedMovieByMovieField(Field.genres, 6F, Direction.ASC);

        moviesDTOList.stream().forEach(System.out::println);

        assertTrue(!moviesDTOList.isEmpty());


    }

    @Test
    void genreMoviesBySubTotal() {

        List<GenreMovie> movies = moviesService.genreMoviesBySubTotal();

        movies.stream().forEach(System.out::println);

        Assertions.assertTrue(!movies.isEmpty());

    }
}