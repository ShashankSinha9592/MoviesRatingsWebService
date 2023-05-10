package com.movies.MoviesRatingsWebService.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Movies {

    @Id
    private String tConst;

    private String titleType;

    private String primaryTitle;

    @Min(value = 0, message = "Runtime minutes cannot be negative")
    private Integer runtimeMinutes;

    private String genres;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "movie")
    private Ratings rating;

}
