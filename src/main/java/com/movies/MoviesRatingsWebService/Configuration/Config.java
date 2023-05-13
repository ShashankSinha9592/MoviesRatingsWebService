package com.movies.MoviesRatingsWebService.Configuration;

import com.movies.MoviesRatingsWebService.Service.MoviesService;
import com.movies.MoviesRatingsWebService.Service.RatingsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class Config {

    @Bean
    public ModelMapper modelMapper(){return new ModelMapper();}

}
