package com.movie.award.controller;


import com.movie.award.DTO.GapResponseDTO;
import com.movie.award.Service.AwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/gaps")
public class AwardController {

    @Autowired
    private AwardService awardService;


    @GetMapping
    public GapResponseDTO getAwardGaps(){
        return awardService.findAwardsWithGaps();
    }
}
