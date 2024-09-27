package com.movie.award.DTO;

import java.util.List;

public record MovieDTO(List<String> producers, List<String> studios, String title, boolean winner) {
}
