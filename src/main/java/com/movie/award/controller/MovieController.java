package com.movie.award.controller;

import com.movie.award.DTO.GapResponseDTO;
import com.movie.award.DTO.MovieDTO;
import com.movie.award.Model.Movie;
import com.movie.award.Service.AwardService;
import com.movie.award.Service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("api/v1/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private AwardService awardService;

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() throws IOException {
        List<Movie> movies = this.movieService.getAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody MovieDTO movieDTO) throws Exception {
        Movie newMovie = movieService.createMovie(movieDTO);
        return new ResponseEntity<>(newMovie, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteMovie(@PathVariable("id") Integer id){
        return movieService.deleteMovie(id);
    }

    @PutMapping("/edit")
    public ResponseEntity<Movie> editMovie(@RequestBody Movie movie) {
        Movie editedMovie =  movieService.editMovie(movie);
        return new ResponseEntity<>(editedMovie, HttpStatus.OK);
    }
}