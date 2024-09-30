package com.movie.award;


import com.movie.award.DTO.MovieDTO;
import com.movie.award.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication

public class MovieAwardApplication {

    @Autowired
    private ResourceLoader resourceLoader;

    public static void main(String[] args) {
        SpringApplication.run(MovieAwardApplication.class, args);
    }

    @Bean
    CommandLineRunner run(MovieService movieService){
        return args -> {

            Resource resource = resourceLoader.getResource("classpath:datafile/movielist (12) (2) (3).csv");
            String line = "";
            String delimiter = ";";
            String listDelimiter = ",";

            try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
                br.readLine();
                while ((line = br.readLine()) != null) {
                    String[] columns = line.split(delimiter);

                    if(columns.length < 4 ){
                        throw new IllegalArgumentException("Invalid CSV format");
                    }

                    int year = Integer.parseInt(columns[0]);
                    String title = columns[1];
                    List<String> studios = Arrays.asList(columns[2].split(listDelimiter));
                    List<String> producers = Arrays.asList(columns[3].split(listDelimiter));
                    boolean winner = false;

                    if(columns.length >= 5){
                        winner = true;
                    }

                    MovieDTO movieDTO = new MovieDTO(year, title, studios, producers, winner);
                    movieService.createMovie(movieDTO);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }
}