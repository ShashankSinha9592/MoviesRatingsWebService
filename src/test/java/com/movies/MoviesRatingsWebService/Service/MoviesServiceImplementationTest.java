package com.movies.MoviesRatingsWebService.Service;

import com.movies.MoviesRatingsWebService.DTO.GenreMovie;
import com.movies.MoviesRatingsWebService.DTO.MoviesDTO;
import com.movies.MoviesRatingsWebService.DTO.MoviesRatingsDTO;
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
    void testGetLongestDurationMovies() {

        List<MoviesRatingsDTO> moviesDTOList = moviesService.getLongestDurationMovies();

        Assertions.assertTrue(10==moviesDTOList.size());

    }

    @Test
    void testGetTopRatedMovie() {

        List<MoviesRatingsDTO> moviesDTOList = moviesService.getTopRatedMovieByMovieField(Field.genres, 6F, Direction.ASC);

        moviesDTOList.stream().forEach(System.out::println);

        assertTrue(!moviesDTOList.isEmpty());


    }

    @Test
    void testGenreMoviesBySubTotal() {

        List<GenreMovie> movies = moviesService.genreMoviesBySubTotal();

        movies.stream().forEach(System.out::println);

        Assertions.assertTrue(!movies.isEmpty());

    }


    @Test
    void testAddNewMovie() {

        MoviesDTO moviesDTO = new MoviesDTO();

        moviesDTO.setGenres("  ");
        moviesDTO.setTConst("1234td");
        moviesDTO.setPrimaryTitle("asasass");
        moviesDTO.setTitleType("sdsfsd");
        moviesDTO.setRuntimeMinutes(1);

        Assertions.assertThrows(Exception.class,()-> moviesService.addNewMovie(moviesDTO));

    }

    @Test
    void testUpdateRunTimeMinutes() {

        Integer ans = moviesService.updateRunTimeMinutes();

        assertTrue(ans>0);

    }
}