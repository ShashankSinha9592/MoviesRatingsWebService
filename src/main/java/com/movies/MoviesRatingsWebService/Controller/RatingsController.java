package com.movies.MoviesRatingsWebService.Controller;

import com.movies.MoviesRatingsWebService.DTO.MoviesDTO;
import com.movies.MoviesRatingsWebService.DTO.RatingsDTO;
import com.movies.MoviesRatingsWebService.Service.RatingsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class RatingsController {

    @Autowired
    RatingsService ratingsService;

    @PostMapping("/give-rating-to-movie/{movieId}")
    public ResponseEntity<String> giveAverageRatingsTOMovie(@Valid @RequestBody RatingsDTO ratingsDTO, @PathVariable String movieId){

        String response = ratingsService.giveAvgRatingToMovie(movieId,ratingsDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

}
