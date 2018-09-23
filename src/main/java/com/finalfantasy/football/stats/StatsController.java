package com.finalfantasy.football.stats;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping(value = "/stats")
public class StatsController {

  private final StatsService statsService;

  public StatsController(final StatsService statsService) {
    this.statsService = statsService;
  }

  @PostMapping(consumes = "application/json", produces = "application/json")
  public String populateDatabaseBySeasonAndWeek(@RequestBody WeekData weekData) {
    try {
      return "We did it! " + statsService.populateDatabaseBySeasonAndWeek(weekData);
    } catch (IOException e) {
      e.printStackTrace();
      return "Damn Jenna";
    }
  }

  @GetMapping(value = "/key")
  public String populateStatKey() {
    try {
      return statsService.populateStatKey();
    } catch (IOException e) {
      e.printStackTrace();
      return "Damn Jenna";
    }
  }
}
