package com.movies.MoviesRatingsWebService.Service;

import com.movies.MoviesRatingsWebService.DTO.GenreMovie;
import com.movies.MoviesRatingsWebService.DTO.MoviesDTO;
import com.movies.MoviesRatingsWebService.DTO.MoviesRatingsDTO;
import com.movies.MoviesRatingsWebService.Exceptions.MoviesException;
import com.movies.MoviesRatingsWebService.Model.Direction;
import com.movies.MoviesRatingsWebService.Model.Field;
import com.movies.MoviesRatingsWebService.Model.Movies;
import com.movies.MoviesRatingsWebService.Repository.MoviesRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class
MoviesServiceImplementation implements MoviesService{

    @Autowired
    private MoviesRepository moviesRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RatingsService ratingsService;

    @Autowired
    @Qualifier(value = "readMoviesCsv")
    ReadCsvFile readCsvFile;

    @PostConstruct
    private void addCsvFileMovies() { readCsvFile.addCsvData();}

    @PreDestroy
    private void removeData(){ moviesRepository.deleteAll(); }

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

    @Transactional
    @Override
    public Integer updateRunTimeMinutes() {

        Integer numOfRowsUpdated = moviesRepository.updateRuntimeMinutes();

        if(numOfRowsUpdated==0) throw new MoviesException("No Rows Updated");

        return numOfRowsUpdated;
    }


    private Movies dtoToMovie(MoviesDTO moviesDTO){
        return modelMapper.map(moviesDTO, Movies.class);
    }
}
