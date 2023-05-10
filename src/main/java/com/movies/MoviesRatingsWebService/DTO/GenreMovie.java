package com.movies.MoviesRatingsWebService.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GenreMovie {

    private String genres;

    private Long moviesCount;

    private Long subTotalNumVotes;

}
