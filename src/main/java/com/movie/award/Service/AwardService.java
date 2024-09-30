package com.movie.award.Service;

import com.movie.award.DTO.GapDTO;
import com.movie.award.DTO.GapResponseDTO;
import com.movie.award.Model.Movie;
import com.movie.award.Repo.MovieRepo;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AwardService {
    private final MovieRepo movieRepo;

    public AwardService(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }
    int minInterval = Integer.MAX_VALUE;
    int maxInterval = 0;

    List<GapDTO> minGaps = new ArrayList<>();
    List<GapDTO> maxGaps = new ArrayList<>();

    public GapResponseDTO findAwardsWithGaps() {
        minGaps.clear();
        maxGaps.clear();
        // Step 1: Fetch the list of winning movies
        List<Movie> winningMovies = movieRepo.findByWinnerTrue();

        // Step 2: Group movies by each individual producer
        Map<String, List<Movie>> moviesByProducer = groupMoviesByProducer(winningMovies);

        // Step 3: Calculate gaps for all producers
        return calculateGapsForProducers(moviesByProducer);
    }

    // 1. Group movies by producer
    private Map<String, List<Movie>> groupMoviesByProducer(List<Movie> winningMovies) {
        Map<String, List<Movie>> moviesByProducer = new HashMap<>();

        for (Movie movie : winningMovies) {
            List<String> producers = movie.getProducers();
            for (String producer : producers) {
                producer = producer.trim();
                moviesByProducer
                        .computeIfAbsent(producer, k -> new ArrayList<>())
                        .add(movie);
            }
        }
        return moviesByProducer;
    }

    // 2. Calculate gaps for all producers
    private GapResponseDTO calculateGapsForProducers(Map<String, List<Movie>> moviesByProducer) {

        for (Map.Entry<String, List<Movie>> entry : moviesByProducer.entrySet()) {
            String producer = entry.getKey();
            List<Movie> movies = entry.getValue();

            if (movies.size() > 1) {
                List<GapDTO> producerMinGaps = new ArrayList<>();
                List<GapDTO> producerMaxGaps = new ArrayList<>();

                // Step 4: Calculate min and max gaps for each producer
                calculateMinMaxGapsForProducer(producer, movies, producerMinGaps, producerMaxGaps);

                // Add gaps to overall min/max lists
                minGaps.addAll(producerMinGaps);
                maxGaps.addAll(producerMaxGaps);
            } else {
                System.out.println("Producer: " + producer + " has only one movie.");
            }
        }

        // Step 5: Return the response DTO with both min and max gaps
        return new GapResponseDTO(minGaps, maxGaps);
    }

    // 3. Calculate min and max gaps for a specific producer
    private void calculateMinMaxGapsForProducer(String producer, List<Movie> movies,
                                                List<GapDTO> producerMinGaps,
                                                List<GapDTO> producerMaxGaps) {

        // Sort movies by nominated year
        movies.sort(Comparator.comparingInt(Movie::getNominatedYear));

        for (int i = 1; i < movies.size(); i++) {
            int previousWinYear = movies.get(i - 1).getNominatedYear();
            int followingWinYear = movies.get(i).getNominatedYear();
            int gap = followingWinYear - previousWinYear;

            // Handle min interval
            if (gap <= minInterval) {
                if (gap < minInterval) {
                    producerMinGaps.clear();  // Clear list when we find a smaller gap
                    minGaps.clear();
                }
                minInterval = gap;
                producerMinGaps.add(new GapDTO(producer, gap, previousWinYear, followingWinYear));
            }

            // Handle max interval
            if (gap >= maxInterval) {
                if (gap > maxInterval) {
                    maxGaps.clear();
                    producerMaxGaps.clear();  // Clear list when we find a larger gap
                }
                maxInterval = gap;
                producerMaxGaps.add(new GapDTO(producer, gap, previousWinYear, followingWinYear));
            }
        }
    }
}
