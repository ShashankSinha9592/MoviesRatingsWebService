package com.movies.MoviesRatingsWebService.Service;

import com.movies.MoviesRatingsWebService.DTO.MoviesDTO;
import com.movies.MoviesRatingsWebService.DTO.RatingsDTO;
import com.movies.MoviesRatingsWebService.Exceptions.MoviesException;
import com.movies.MoviesRatingsWebService.Model.Direction;
import com.movies.MoviesRatingsWebService.Model.Ratings;
import com.movies.MoviesRatingsWebService.Repository.RatingsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RatingsServiceImplementation implements RatingsService{

    @Autowired
    RatingsRepository ratingsRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<RatingsDTO> addRatingsFromCsvFile() {
        try(BufferedReader br = new BufferedReader((new FileReader("src/main/resources/ratings.csv")))){

            br.lines()
                    .map((rating)-> rating.split(","))
                    .filter((rating)-> !rating[0].equals("tconst"))
                    .forEach((rating)->{

                        Ratings ratings = new Ratings();
                        ratings.setTconst(rating[0]);
                        ratings.setAverageRating(Float.valueOf(rating[1]));
                        ratings.setNumVotes(Integer.valueOf(rating[2]));

                        ratingsRepository.save(ratings);
                    });

            return ratingsList();

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public String giveAvgRatingToMovie(String movieId, RatingsDTO ratingsDTO) {
        return null;
    }

    @Override
    public List<MoviesDTO> getTopRatedMovieByRatingField(Float rating, Sort sort) {

        System.out.println("rating called");

        List<MoviesDTO> sortedTopRatedMoviesByRating = ratingsRepository.getTopRatedMovieInSortedOrderByField(rating, sort);

        if(sortedTopRatedMoviesByRating.isEmpty()) throw new MoviesException("No Movies found");

        return sortedTopRatedMoviesByRating;

    }

    private List<RatingsDTO> ratingsList() {

       List<Ratings> ratings =  ratingsRepository.findAll();

       List<RatingsDTO> ratingsDTOS = new ArrayList<>();

       ratings.forEach((rating)-> ratingsDTOS.add(ratingsToDTO(rating)));

       return ratingsDTOS;

    }

    private RatingsDTO ratingsToDTO(Ratings ratings){

        return modelMapper.map(ratings, RatingsDTO.class);

    }

}
