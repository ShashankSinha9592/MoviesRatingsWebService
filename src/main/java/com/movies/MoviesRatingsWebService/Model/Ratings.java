package com.movies.MoviesRatingsWebService.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.metamodel.mapping.ForeignKeyDescriptor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ratings {

    @Id
    private String tconst;

    private Float averageRating;

    private Integer numVotes;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Movies movie;


}
