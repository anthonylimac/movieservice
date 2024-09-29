package com.movie.award.DTO;

import java.util.List;

public record MovieDTO( int nominatedYear,String title,List<String> studios, List<String> producers,boolean winner) {
}
