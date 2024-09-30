package com.movie.award.DTO;

import java.util.List;

public record GapResponseDTO(List<GapDTO> min, List<GapDTO> max) {
}
