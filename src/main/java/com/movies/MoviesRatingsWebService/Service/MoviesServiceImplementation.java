package com.movies.MoviesRatingsWebService.Service;

import com.movies.MoviesRatingsWebService.DTO.GenreMovie;
import com.movies.MoviesRatingsWebService.DTO.MoviesDTO;
import com.movies.MoviesRatingsWebService.DTO.MoviesRatingsDTO;
import com.movies.MoviesRatingsWebService.Exceptions.MoviesException;
import com.movies.MoviesRatingsWebService.Model.Direction;
import com.movies.MoviesRatingsWebService.Model.Field;
import com.movies.MoviesRatingsWebService.Model.Movies;
import com.movies.MoviesRatingsWebService.Repository.MoviesRepository;
import jakarta.validation.Valid;
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
public class MoviesServiceImplementation implements MoviesService{

    @Autowired
    MoviesRepository moviesRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    RatingsService ratingsService;

    @Override
    public List<MoviesDTO> addCsvFileMovies() {

        List<Movies> moviesList = moviesRepository.findAll();
        if(!moviesList.isEmpty()) throw new MoviesException("Data already added from CSV file, cannot add duplicate data to the database");

        try(BufferedReader br = new BufferedReader((new FileReader("src/main/resources/movies.csv")))){

           br.lines()
                   .map((movie)-> movie.split(","))
                   .filter((movie)-> !movie[0].equals("tconst"))
                   .forEach((movie)->{

                       Movies movies = new Movies();
                       movies.setTConst(movie[0]);
                       movies.setTitleType(movie[1]);
                       movies.setPrimaryTitle(movie[2]);
                       movies.setRuntimeMinutes(Integer.valueOf(movie[3]));
                       movies.setGenres(movie[4]);

                       moviesRepository.save(movies);
                    });

           return movieList();

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public List<MoviesRatingsDTO> getLongestDurationMovies() {

        List<MoviesRatingsDTO> top10LongestDurationMovies = moviesRepository.getTop10LongestDurationMovies();

        if(top10LongestDurationMovies.isEmpty()) throw new MoviesException("No movies found");

        return top10LongestDurationMovies;

    }

    @Override
    public String addNewMovie(@Valid MoviesDTO moviesDto) {

        Movies movies = dtoToMovie(moviesDto);

        moviesRepository.save(movies);

        return "Success";

    }

    @Override
    public List<MoviesRatingsDTO> getTopRatedMovieByMovieField(Field field, Float rating, Direction direction) {

        Sort sort = direction.equals(Direction.ASC)? Sort.by(field.name()).ascending() : Sort.by(field.name()).descending();

        List<MoviesRatingsDTO> sortedTopRatedMovies;

        if(field.equals(Field.averageRating) || field.equals(Field.numVotes)) sortedTopRatedMovies = ratingsService.getTopRatedMovieByRatingField(rating, sort);

        else sortedTopRatedMovies = moviesRepository.getTopRatedMovieInSortedOrderByField(rating, sort);

        if(sortedTopRatedMovies.isEmpty()) throw new MoviesException("No movies found");

        return sortedTopRatedMovies;

    }

    @Override
    public List<GenreMovie> genreMoviesBySubTotal() {

        List<GenreMovie> movies = moviesRepository.getAllMoviesByGenreAndTotalNumCount();

        if(movies.isEmpty()) throw new MoviesException("No movies found");

        return movies;

    }

    @Override
    public Integer updateRunTimeMinutes() {

        Integer numOfRowsUpdated = moviesRepository.updateRuntimeMinutes();

        if(numOfRowsUpdated==0) throw new MoviesException("No Rows Updated");

        return numOfRowsUpdated;
    }

    private List<MoviesDTO> movieList(){

        List<MoviesDTO> moviesDTOList = new ArrayList<>();

        List<Movies> savedMovies = moviesRepository.findAll();

        savedMovies.forEach((movie)-> moviesDTOList.add(movieToDTO(movie)));

        return moviesDTOList;

    }

    private MoviesDTO movieToDTO(Movies movies){

        return modelMapper.map(movies, MoviesDTO.class);

    }

    private Movies dtoToMovie(MoviesDTO moviesDTO){
        return modelMapper.map(moviesDTO, Movies.class);
    }
}
