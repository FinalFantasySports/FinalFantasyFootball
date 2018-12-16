package com.finalfantasy.football.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalfantasy.football.NflFantasyApiConfiguration;
import com.finalfantasy.football.players.PlayersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Map;

import static java.util.Objects.nonNull;

@Service
public class ApiService {

  private static final Logger log = LoggerFactory.getLogger(ApiService.class);

  private final RestTemplate restTemplate;
  private final NflFantasyApiConfiguration apiConfiguration;
  private final PlayersService playersService;

  public ApiService(final RestTemplate restTemplate, final NflFantasyApiConfiguration apiConfiguration, final PlayersService playersService) {
    this.restTemplate = restTemplate;
    this.apiConfiguration = apiConfiguration;
    this.playersService = playersService;
  }

  public void getPlayers(short season) {
    var url = apiConfiguration.getPlayerInfoRoute(season);
    log.debug("Getting players for the {} season from url: {}", season, url);
    var response = restTemplate.getForObject(url, Map.class);
    if (nonNull(response)) {
      var playersCollObect = response.get("players");
      var mapper = new ObjectMapper();
      var playersCollection = mapper.convertValue(playersCollObect, Collection.class);
      log.debug("Sending off player collection to sorted: {}", playersCollection.toString());
      sortPlayers(playersCollection, season);
    }


  }

  private void sortPlayers(Collection<Map> playerMaps, short season) {
    log.debug("sorting playerMaps: {}", playerMaps.toString());
    playerMaps.stream().forEach( mapOfPlayer -> playersService.savePlayerFromMap(mapOfPlayer, season));
  }
}
