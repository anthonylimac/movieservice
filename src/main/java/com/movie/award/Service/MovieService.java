package com.movie.award.Service;

import com.movie.award.DTO.MovieDTO;
import com.movie.award.Model.Movie;
import com.movie.award.Repo.MovieRepo;
import com.movie.award.exceptions.InvalidDataAccessException;
import com.movie.award.exceptions.MovieAlreadyCreatedException;
import com.movie.award.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepo movieRepo;

    @Autowired
    public MovieService(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    public boolean findMovieByTitle(String title) {
        Optional<Movie> movie = movieRepo.findMovieByTitle(title);
       return movie.isPresent();
    }

    public Movie createMovie(MovieDTO movieDTO) throws Exception {
        if (findMovieByTitle(movieDTO.title())) {
            throw new MovieAlreadyCreatedException("This movie title already exists");
        } else {
            Movie newMovie = new Movie(movieDTO);
            try {
                movieRepo.save(newMovie);
            } catch (Exception e) {
                throw new Exception("Unable to save");
            }
            return newMovie;
        }
    }

    public List<Movie> getAllMovies() {
        return movieRepo.findAll();
    }

    public String deleteMovie(Integer id) {
        Optional<Movie> movieToDelete = movieRepo.findById(id);
        if (movieToDelete.isPresent()) {
            movieRepo.deleteById(id);
        }
        return "Movie Deleted";
    }

    public Movie editMovie(Movie editedMovie) {
        if (editedMovie.getId() == null || editedMovie.getNominatedYear() <= 0 || editedMovie.getStudios() == null || editedMovie.getProducers() == null || editedMovie.getTitle() == null) {
            throw new InvalidDataAccessException("field must not be empty");
        }

        Movie existingMovie = movieRepo.findById(editedMovie.getId()).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        existingMovie.setProducers(editedMovie.getProducers());
        existingMovie.setStudios(editedMovie.getStudios());
        existingMovie.setWinner(editedMovie.isWinner());
        existingMovie.setTitle(editedMovie.getTitle());
        existingMovie.setNominatedYear(editedMovie.getNominatedYear());

        movieRepo.save(existingMovie);
        return existingMovie;
    }
}