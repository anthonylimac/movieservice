package com.movie.award.Model;

import com.movie.award.DTO.MovieDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "movie", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
public class Movie {

    @Id
    @SequenceGenerator(name = "movie_id_sequence", sequenceName = "movie_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_id_sequence")
    private Integer id;

    private List<String> producers;
    private List<String> studios;

    private String title;
    private boolean winner;
    private int nominatedYear;

    public Movie(MovieDTO movieDTO){
        this.producers = movieDTO.producers();
        this.studios = movieDTO.studios();
        this.title = movieDTO.title();
        this.winner = movieDTO.winner();
        this.nominatedYear = movieDTO.nominatedYear();
    }

    public Movie() {
    }

    public Movie(Integer id, List<String> producers, List<String> studios, String title, boolean winner, int nominatedYear) {
        this.id = id;
        this.producers = producers;
        this.studios = studios;
        this.title = title;
        this.winner = winner;
        this.nominatedYear = nominatedYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return winner == movie.winner && nominatedYear == movie.nominatedYear && Objects.equals(id, movie.id) && Objects.equals(producers, movie.producers) && Objects.equals(studios, movie.studios) && Objects.equals(title, movie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, producers, studios, title, winner, nominatedYear);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", producers=" + producers +
                ", studios=" + studios +
                ", title='" + title + '\'' +
                ", winner=" + winner +
                ", nominatedYear=" + nominatedYear +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getProducers() {
        return producers;
    }

    public void setProducers(List<String> producers) {
        this.producers = producers;
    }

    public List<String> getStudios() {
        return studios;
    }

    public void setStudios(List<String> studios) {
        this.studios = studios;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public int getNominatedYear() {
        return nominatedYear;
    }

    public void setNominatedYear(int nominatedYear) {
        this.nominatedYear = nominatedYear;
    }
}
