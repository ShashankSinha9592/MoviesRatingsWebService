package com.movies.MoviesRatingsWebService.Exceptions;

public class MoviesException extends RuntimeException{

    public MoviesException() {
    }

    public MoviesException(String message) {
        super(message);
    }
}
