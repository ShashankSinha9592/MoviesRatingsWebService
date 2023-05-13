package com.movies.MoviesRatingsWebService.Service;

import com.movies.MoviesRatingsWebService.Model.Movies;
import com.movies.MoviesRatingsWebService.Repository.MoviesRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Component
public class ReadMoviesCsv implements ReadCsvFile{

    @Autowired
    MoviesRepository moviesRepository;

    @Override
    public void addCsvData() {

        List<Movies> movies = moviesRepository.findAll();

        if(!movies.isEmpty()) return;

        try(CSVParser parse = CSVFormat.DEFAULT
                .builder().setHeader("tconst", "titleType", "primaryTitle", "runtimeMinutes", "genres")
                .setSkipHeaderRecord(true).build()
                .parse(new FileReader("src/main/resources/movies.csv"))) {

            parse.stream().forEach((movie) ->setData(movie));

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    private void setData(CSVRecord record){

        Movies movies = new Movies();

        movies.setTConst(record.get("tconst"));
        movies.setTitleType(record.get("titleType"));
        movies.setPrimaryTitle(record.get("primaryTitle"));
        movies.setRuntimeMinutes(Integer.valueOf(record.get("runtimeMinutes")));
        movies.setGenres(record.get("genres"));

        moviesRepository.save(movies);

    }

}
