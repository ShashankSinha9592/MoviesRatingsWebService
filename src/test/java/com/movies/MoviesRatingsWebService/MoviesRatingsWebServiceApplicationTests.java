package com.movies.MoviesRatingsWebService;

import com.movies.MoviesRatingsWebService.Repository.MoviesRepository;
import com.movies.MoviesRatingsWebService.Service.MoviesService;
import com.movies.MoviesRatingsWebService.Service.MoviesServiceImplementation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

@SpringBootTest
class MoviesRatingsWebServiceApplicationTests {

	@Autowired
	MoviesService moviesService;

	@Test
	void contextLoads() {

		moviesService.addCsvFileMovies();

	}

}
