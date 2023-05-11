package com.movies.MoviesRatingsWebService.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MoviesRatingsDTO {

    private String tConst;

    private String primaryTitle;

    private Integer runtimeMinutes;

    private String genres;

    private Float averageRating;

}
