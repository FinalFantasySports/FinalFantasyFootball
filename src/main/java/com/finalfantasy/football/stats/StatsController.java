package com.finalfantasy.football.stats;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/stats")
public class StatsController {

  private final StatsService statsService;

  public StatsController(final StatsService statsService) {
    this.statsService = statsService;
  }

  @RequestMapping
  public String populateDatabaseBySeasonAndWeek() {
    try {
      return "We did it! " + statsService.populateDatabaseBySeasonAndWeek();
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
