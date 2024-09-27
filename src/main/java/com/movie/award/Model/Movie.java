package com.movie.award.Model;

import com.movie.award.DTO.MovieDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

import java.util.List;

@Entity
public class Movie {

    @Id
    @SequenceGenerator(name = "movie_id_sequence", sequenceName ="movie_id_sequence" )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "movie_id_sequence")
    private Integer id;

    private List<String> producers;
    private List<String> studios;
    private String title;
    private boolean winner;

    public Movie(MovieDTO movieDTO){
        this.producers = movieDTO.producers();
        this.studios = movieDTO.studios();
        this.title = movieDTO.title();
        this.winner = movieDTO.winner();
    }

    public Movie(Integer id, List<String> producers, List<String> studios, String title, boolean winner) {
        this.id = id;
        this.producers = producers;
        this.studios = studios;
        this.title = title;
        this.winner = winner;
    }

    public Movie() {
    }
}
