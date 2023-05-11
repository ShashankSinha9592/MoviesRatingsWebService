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

    @NotNull(message = "tConst is null")
    @NotEmpty(message = "tConst is empty")
    @NotBlank(message = "tConst is blank")
    private String tConst;

    @NotNull(message = "Title is null")
    @NotEmpty(message = "Title is empty")
    @NotBlank(message = "Title is blank")
    private String titleType;

    @NotNull(message = "primaryTitle is null")
    @NotEmpty(message = "primaryTitle is empty")
    @NotBlank(message = "primaryTitle is blank")
    private String primaryTitle;

    @Min(value = 0, message = "Runtime minutes cannot be negative")
    @NotNull(message = "runtimeMinutes is null")
    private Integer runtimeMinutes;

    @NotNull(message = "genres is null")
    @NotEmpty(message = "genres is empty")
    @NotBlank(message = "genres is blank")
    private String genres;


}
