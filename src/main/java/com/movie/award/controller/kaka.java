package com.movie.award.controller;

import java.util.*;
import java.util.stream.Collectors;



    class Movie {
        String producer;
        int year;
        boolean won;

        public Movie(String producer, int year, boolean won) {
            this.producer = producer;
            this.year = year;
            this.won = won;
        }

        public String getProducer() {
            return producer;
        }

        public int getYear() {
            return year;
        }

        public boolean hasWon() {
            return won;
        }
    }

    class ProducerAwardsAnalyzer {
        private List<Movie> movies;

        public ProducerAwardsAnalyzer(List<Movie> movies) {
            this.movies = movies;
        }

        public String getProducerWithMinTimeBetweenWins() {
            return findProducerWithExtremeTimeBetweenWins(true);
        }

        public String getProducerWithMaxTimeBetweenWins() {
            return findProducerWithExtremeTimeBetweenWins(false);
        }

        private String findProducerWithExtremeTimeBetweenWins(boolean findMin) {
            Map<String, List<Integer>> producerWins = movies.stream()
                    .filter(Movie::hasWon)
                    .collect(Collectors.groupingBy(Movie::getProducer,
                            Collectors.mapping(Movie::getYear, Collectors.toList())));

            String resultProducer = null;
            int extremeTime = findMin ? Integer.MAX_VALUE : Integer.MIN_VALUE;

            for (Map.Entry<String, List<Integer>> entry : producerWins.entrySet()) {
                List<Integer> years = entry.getValue();
                if (years.size() < 2) continue; // Need at least 2 wins

                Collections.sort(years);
                for (int i = 1; i < years.size(); i++) {
                    int timeBetween = years.get(i) - years.get(i - 1);
                    if ((findMin && timeBetween < extremeTime) || (!findMin && timeBetween > extremeTime)) {
                        extremeTime = timeBetween;
                        resultProducer = entry.getKey();
                    }
                }
            }
            return resultProducer;
        }

        public static void main(String[] args) {
            List<Movie> movies = Arrays.asList(
                    new Movie("Producer A", 1991, true),
                    new Movie("Producer A", 2000, true),
                    new Movie("Producer B", 2001, true),
                    new Movie("Producer B", 2006, true),
                    new Movie("Producer C", 2003, true),
                    new Movie("Producer C", 2010, true),
                    new Movie("Producer D", 1900, true),
                    new Movie("Producer D", 2002, true),
                    new Movie("Producer E", 2021, true),
                    new Movie("Producer E", 2022, true)

            );

            ProducerAwardsAnalyzer analyzer = new ProducerAwardsAnalyzer(movies);

            String minTimeProducer = analyzer.getProducerWithMinTimeBetweenWins();
            String maxTimeProducer = analyzer.getProducerWithMaxTimeBetweenWins();

            System.out.println("Producer with minimum gap between wins: " + minTimeProducer);
            System.out.println("Producer with maximum gap between wins: " + maxTimeProducer);
        }
    }

