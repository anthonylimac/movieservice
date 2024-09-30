package com.movie.award.Repo;

import com.movie.award.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepo extends JpaRepository<Movie, Integer> {

    Optional<Movie> findMovieByTitle (String title);
    List<Movie> findByWinnerTrue();
}
