package com.movies.MoviesRatingsWebService.DTO;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RatingsDTO {

    private String tconst;

    private Float averageRating;

    private Integer numVotes;

}
