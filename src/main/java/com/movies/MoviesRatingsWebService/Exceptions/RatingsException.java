package com.movies.MoviesRatingsWebService.Exceptions;

public class RatingsException extends RuntimeException{

    public RatingsException() {
    }

    public RatingsException(String message) {
        super(message);
    }
}
