package com.movie.award.Service;


import com.movie.award.DTO.MovieDTO;
import com.movie.award.Model.Movie;
import com.movie.award.Repo.MovieRepo;
import com.movie.award.exceptions.InvalidDataAccessException;
import com.movie.award.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepo movieRepo;

    @Autowired
    public MovieService(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    public Movie createMovie(MovieDTO movieDTO) throws Exception {
        Movie newMovie = new Movie(movieDTO);
        try {
            movieRepo.save(newMovie);
        } catch (Exception e) {
            throw new Exception("Unable to save");
        }
        return newMovie;
    }

    public List<Movie> getAllMovies() {
        return movieRepo.findAll();
    }

    public String deleteMovie(Integer id) {
        Optional<Movie> movieToDelete = movieRepo.findById(id);
        if (movieToDelete.isPresent()) {
            movieRepo.deleteById(id);
        }
        return "movie Deleted";
    }

    public Movie editMovie(Movie editedMovie) {
        if (editedMovie.getId() == null || editedMovie.getNominatedYear()<= 0 || editedMovie.getStudios() == null || editedMovie.getProducers() == null || editedMovie.getTitle() == null){
            throw  new InvalidDataAccessException("field must not be empty");
        }
        Movie movie = movieRepo.findById(editedMovie.getId()).orElseThrow(()-> new UserNotFoundException("User Not Found"));
        movie = editedMovie;
        movieRepo.save(movie);
        return movie;
    }
}
