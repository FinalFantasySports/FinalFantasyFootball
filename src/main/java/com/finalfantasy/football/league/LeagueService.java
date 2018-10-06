package com.finalfantasy.football.league;

import org.springframework.stereotype.Service;

@Service
public class LeagueService {

  public LeagueScoring getLeague() {
    return new YahooLeagueScoring();
  }


}
