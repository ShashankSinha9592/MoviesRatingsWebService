package com.movies.MoviesRatingsWebService.DTO;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MoviesDTO {

    private String tConst;

    @NotNull(message = "Title is null")
    @NotEmpty(message = "Title is empty")
    @NotBlank(message = "Title is blank")
    private String titleType;

    private String primaryTitle;

    @Min(value = 0, message = "Runtime minutes cannot be negative")
    private Integer runtimeMinutes;

    private String genres;

    private Float averageRating;

    private Integer numVotes;

}
