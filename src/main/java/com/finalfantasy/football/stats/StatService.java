package com.finalfantasy.football.stats;

import org.springframework.stereotype.Service;

@Service
public class StatService {

  private final StatsByWeekRepository repository;

  public StatService(final StatsByWeekRepository repository) {
    this.repository = repository;
  }

//  public void saveStatFromMap(Map statMap, short season, short week) {
//    StatsByWeek stat = new S
//  }
}
