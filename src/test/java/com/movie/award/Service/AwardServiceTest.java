package com.movie.award.Service;

import com.movie.award.DTO.GapResponseDTO;
import com.movie.award.Model.Movie;
import com.movie.award.Repo.MovieRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
public class AwardServiceTest {

    @MockBean
    private MovieRepo movieRepo;

    @Autowired
    private AwardService awardService;

    public List<Movie> getMockedMovies(){
        Movie movie1 = new Movie(1, Arrays.asList("Producer 1"), Arrays.asList("Studio"), "Movie 1", true, 2000);
        Movie movie2 = new Movie(2, Arrays.asList("Producer 1"), Arrays.asList("Studio"), "Movie 2", true, 2005);
        Movie movie3 = new Movie(3, Arrays.asList("Producer 1"), Arrays.asList("Studio"), "Movie 3", true, 2010);
        Movie movie4 = new Movie(1, Arrays.asList("Producer 2"), Arrays.asList("Studio"), "Movie 1", true, 2000);
        Movie movie5 = new Movie(2, Arrays.asList("Producer 2"), Arrays.asList("Studio"), "Movie 2", true, 2005);
        Movie movie6 = new Movie(3, Arrays.asList("Producer 2"), Arrays.asList("Studio"), "Movie 3", true, 2010);
        return Arrays.asList(movie1,movie2,movie3,movie4,movie5,movie6);

    }

    @Test
    public void testFindAwardsWithGaps() {
        when(movieRepo.findByWinnerTrue()).thenReturn(getMockedMovies());
        // Execute the method to find gaps in awards
        GapResponseDTO result = awardService.findAwardsWithGaps();

        // Verify that the result is not null and has valid data
        assertNotNull(result);

        // Ensure min and max gaps are not empty
        assertFalse(result.min().isEmpty());
        assertFalse(result.max().isEmpty());

        // Check if min/max gaps are calculated correctly
        assertTrue(result.min().stream().anyMatch(gap -> gap.producer().equals("Producer 1") && gap.interval() == 5));
        assertTrue(result.max().stream().anyMatch(gap -> gap.producer().equals("Producer 1") && gap.interval() == 5));

        // Ensure gaps for producer 2 are also found
        assertTrue(result.min().stream().anyMatch(gap -> gap.producer().equals("Producer 2") && gap.interval() == 5));
        assertTrue(result.max().stream().anyMatch(gap -> gap.producer().equals("Producer 2") && gap.interval() == 5));

        // Verify there are no producers with only one movie (no gaps)
        assertTrue(result.min().stream().noneMatch(gap -> gap.producer().equals("Producer 3")));
        assertTrue(result.max().stream().noneMatch(gap -> gap.producer().equals("Producer 3")));
    }

    @Test
    public void testCorrectMinAndMaxGapCalculation() {
        when(movieRepo.findByWinnerTrue()).thenReturn(getMockedMovies());

        // Call the service method
        GapResponseDTO result = awardService.findAwardsWithGaps();

        // Assert that the min gap is 5 years (between 2000 and 2005)
        assertTrue(result.min().stream().anyMatch(gap -> gap.producer().equals("Producer 1") && gap.interval() == 5));

        // Assert that the max gap is also 5 years (between 2005 and 2010)
        assertTrue(result.max().stream().anyMatch(gap -> gap.producer().equals("Producer 1") && gap.interval() == 5));
    }

    @Test
    public void testMultipleProducersWithSameGap() {
        when(movieRepo.findByWinnerTrue()).thenReturn(getMockedMovies());

        // Call the service method
        GapResponseDTO result = awardService.findAwardsWithGaps();

        // Assert that both producers have a gap of 5 years
        assertTrue(result.min().stream().anyMatch(gap -> gap.producer().equals("Producer 1") && gap.interval() == 5));
        assertTrue(result.min().stream().anyMatch(gap -> gap.producer().equals("Producer 2") && gap.interval() == 5));
    }
}
