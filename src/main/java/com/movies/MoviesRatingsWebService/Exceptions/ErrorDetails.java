package com.movies.MoviesRatingsWebService.Exceptions;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorDetails {

    private LocalDateTime timeStamp;

    private String message;

    private String description;

}
