package com.movies.MoviesRatingsWebService.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.sql.SQLException;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MoviesException.class)
    public ResponseEntity<ErrorDetails> moviesExceptionHandler(MoviesException exc, WebRequest req){

        ErrorDetails err = new ErrorDetails();
        err.setTimeStamp(LocalDateTime.now());
        err.setMessage(exc.getMessage());
        err.setDescription(req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(RatingsException.class)
    public ResponseEntity<ErrorDetails> ratingsExceptionHandler(RatingsException exc, WebRequest req){

        ErrorDetails err = new ErrorDetails();
        err.setTimeStamp(LocalDateTime.now());
        err.setMessage(exc.getMessage());
        err.setDescription(req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ErrorDetails> sqlExceptionHandler(SQLException exc, WebRequest req){

        ErrorDetails err = new ErrorDetails();
        err.setTimeStamp(LocalDateTime.now());
        err.setMessage(exc.getMessage());
        err.setDescription(req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> genericExceptionHandler(Exception exc, WebRequest req){

        ErrorDetails err = new ErrorDetails();
        err.setTimeStamp(LocalDateTime.now());
        err.setMessage(exc.getMessage());
        err.setDescription(req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> methodArgumentExceptionHandler(MethodArgumentNotValidException me, WebRequest req)  {

        ErrorDetails err=new ErrorDetails();
        err.setTimeStamp(LocalDateTime.now());
        err.setDescription(req.getDescription(false));
        err.setMessage(me.getBindingResult().getFieldError().getDefaultMessage());

        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorDetails> noHandlerExceptionHandler(NoHandlerFoundException me, WebRequest req)  {

        ErrorDetails err=new ErrorDetails();
        err.setTimeStamp(LocalDateTime.now());
        err.setDescription(req.getDescription(false));
        err.setMessage(me.getMessage());

        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);

    }

}

