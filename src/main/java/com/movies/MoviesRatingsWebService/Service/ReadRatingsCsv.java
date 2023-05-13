package com.movies.MoviesRatingsWebService.Service;

import com.movies.MoviesRatingsWebService.Model.Ratings;
import com.movies.MoviesRatingsWebService.Repository.RatingsRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Component
public class ReadRatingsCsv implements ReadCsvFile{

    @Autowired
    RatingsRepository ratingsRepository;

    @Override
    public void addCsvData() {

        List<Ratings> ratingsList = ratingsRepository.findAll();

        if(!ratingsList.isEmpty()) return;

        try (CSVParser parse = CSVFormat.DEFAULT
                .builder().setHeader("tconst", "averageRating","numVotes")
                .setSkipHeaderRecord(true).build()
                .parse(new FileReader("src/main/resources/ratings.csv"))){

            parse.stream().forEach((rating) ->setData(rating));

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    private void setData(CSVRecord record){

        Ratings ratings = new Ratings();

        ratings.setTconst(record.get("tconst"));
        ratings.setAverageRating(Float.valueOf(record.get("averageRating")));
        ratings.setNumVotes(Integer.valueOf(record.get("numVotes")));

        ratingsRepository.save(ratings);

    }

}
