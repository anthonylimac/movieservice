package com.movie.award.Service;


import com.movie.award.DTO.MovieDTO;
import com.movie.award.Model.Movie;
import com.movie.award.Repo.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepo movieRepo;

    @Autowired
    public MovieService(MovieRepo movieRepo){
        this.movieRepo = movieRepo;
    }

    public Movie createMovie(MovieDTO movieDTO) throws Exception {
        Movie newMovie = new Movie(movieDTO);
        try{
            movieRepo.save(newMovie);
        }catch (Exception e){
            throw new Exception("Unable to save");
        }
        return newMovie;
    }

    public List<Movie> getAllMovies(){
        return movieRepo.findAll();
    }

    public String deleteMovie(Integer id){
        Optional<Movie> movieToDelete = movieRepo.findById(id);
        if(movieToDelete.isPresent()){
            movieRepo.deleteById(id);
        }
        return "movie Deleted";
    }

}
