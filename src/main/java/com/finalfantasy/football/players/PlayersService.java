package com.finalfantasy.football.players;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PlayersService {

  private static final Logger log = LoggerFactory.getLogger(PlayersService.class);
  private static final String apiRoute = "http://api.fantasy.nfl.com/v1/players/stats?statType=seasonStats&season=2010&week=1&format=json";

  private final RestTemplate restTemplate;

  public PlayersService(final RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public boolean populateDatabaseBySeasonAndWeek() {

    String playersRespone = restTemplate.getForObject(apiRoute, String.class);
    log.debug("playersResponse.length : {}", playersRespone.length());
    return false;
  }
}
