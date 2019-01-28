package com.finalfantasy.football.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/populate-database")
public class DataPopulationController {

  private final ApiService apiService;

  public DataPopulationController(final ApiService apiService) {
    this.apiService = apiService;
  }

  @PostMapping
  public ResponseEntity addStatsFromThisWeek(@RequestBody StatRequest statRequest) {
    apiService.getStats(statRequest.season, statRequest.week);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
