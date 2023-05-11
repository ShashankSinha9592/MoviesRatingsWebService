package com.movies.MoviesRatingsWebService.Service;

import com.movies.MoviesRatingsWebService.DTO.RatingsDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RatingsServiceImplementationTest {

    @Autowired
    RatingsService ratingsService;

    @Test
    void addRatingsFromCsvFile() {

        List<RatingsDTO> ratingsDTOS = ratingsService.addRatingsFromCsvFile();
        assertTrue(!ratingsDTOS.isEmpty());

    }

}