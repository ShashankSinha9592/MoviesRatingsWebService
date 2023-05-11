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
    @Column(unique = true, nullable = false)
    private String tConst;

    @Column(nullable = false)
    private String titleType;

    @Column(nullable = false)
    private String primaryTitle;

    @Column(nullable = false)
    private Integer runtimeMinutes;

    @Column(nullable = false)
    private String genres;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "movie")
    private Ratings rating;

}
