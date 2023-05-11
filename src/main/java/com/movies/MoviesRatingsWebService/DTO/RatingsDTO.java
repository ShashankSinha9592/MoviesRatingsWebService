package com.movies.MoviesRatingsWebService.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RatingsDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String tconst;

    @Min(value = 0, message = "average rating cannot be nagative")
    @NotNull(message = "average rating is null")
    private Float averageRating;

    @Min(value = 1, message = "Votes cannot be less than 1")
    @NotNull(message = "num votes is null")
    private Integer numVotes;

}
