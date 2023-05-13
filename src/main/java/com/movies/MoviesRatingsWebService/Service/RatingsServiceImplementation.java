package com.movies.MoviesRatingsWebService.Service;

import com.movies.MoviesRatingsWebService.DTO.MoviesRatingsDTO;
import com.movies.MoviesRatingsWebService.DTO.RatingsDTO;
import com.movies.MoviesRatingsWebService.Exceptions.MoviesException;
import com.movies.MoviesRatingsWebService.Model.Movies;
import com.movies.MoviesRatingsWebService.Model.Ratings;
import com.movies.MoviesRatingsWebService.Repository.MoviesRepository;
import com.movies.MoviesRatingsWebService.Repository.RatingsRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class RatingsServiceImplementation implements RatingsService{

    @Autowired
    private RatingsRepository ratingsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MoviesRepository moviesRepository;

    @Autowired
    @Qualifier(value = "readRatingsCsv")
    ReadCsvFile readCsvFile;

    @PostConstruct
    private void addRatingsFromCsvFile() { readCsvFile.addCsvData();}

    @PreDestroy
    private void removeData(){ ratingsRepository.deleteAll();}


    @Override
    public String giveAvgRatingToMovie(String movieId, RatingsDTO ratingsDTO) {

        Movies movies = moviesRepository.findById(movieId).orElseThrow(()-> new MoviesException("Invalid movie Id : "+movieId));

        Ratings ratings = DtoToRatings(ratingsDTO);

        ratings.setTconst(movies.getTConst());
        ratings.setMovie(movies);

        movies.setRating(ratings);
        moviesRepository.save(movies);

        return "Success";

    }

    @Override
    public List<MoviesRatingsDTO> getTopRatedMovieByRatingField(Float rating, Sort sort) {

        System.out.println("rating called");

        List<MoviesRatingsDTO> sortedTopRatedMoviesByRating = ratingsRepository.getTopRatedMovieInSortedOrderByField(rating, sort);

        if(sortedTopRatedMoviesByRating.isEmpty()) throw new MoviesException("No Movies found");

        return sortedTopRatedMoviesByRating;

    }

    private List<RatingsDTO> ratingsList() {

       List<Ratings> ratings =  ratingsRepository.findAll();

       List<RatingsDTO> ratingsDTOS = new ArrayList<>();

       ratings.forEach((rating)-> ratingsDTOS.add(ratingsToDTO(rating)));

       return ratingsDTOS;

    }

    private RatingsDTO ratingsToDTO(Ratings ratings){ return modelMapper.map(ratings, RatingsDTO.class);}

    private Ratings DtoToRatings(RatingsDTO ratingsDTO){
        return modelMapper.map(ratingsDTO, Ratings.class);
    }

}
