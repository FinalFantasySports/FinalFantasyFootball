package com.finalfantasy.football.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalfantasy.football.players.PlayersService;
import com.finalfantasy.football.stats.StatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import static com.finalfantasy.football.NflFantasyApiConfiguration.getPlayerInfoRoute;
import static com.finalfantasy.football.NflFantasyApiConfiguration.getStatsFor;
import static com.finalfantasy.football.NflFantasyApiConfiguration.getStatsKeyRoute;
import static java.util.Objects.nonNull;

@Service
public class ApiService {

  private static final Logger log = LoggerFactory.getLogger(ApiService.class);

  private final RestTemplate restTemplate;
  private final PlayersService playersService;
  private final StatService statService;
  private final ObjectMapper mapper;

  public ApiService(final RestTemplate restTemplate, final PlayersService playersService, final StatService statService) {
    this.restTemplate = restTemplate;
    this.playersService = playersService;
    this.statService = statService;
    mapper = new ObjectMapper();
  }

  public void getStats(short season, Short week) {
    var url = getStatsFor(season, week);
    var response = restTemplate.getForObject(url, Map.class);
    if (nonNull(response)) {
      var statsCollObject = response.get("players");
      var statsCollection = mapper.convertValue(statsCollObject, Collection.class);

    }
  }

  public JsonNode getStatKeys() throws IOException {
    var response = restTemplate.getForObject(getStatsKeyRoute(), String.class);
    if (nonNull(response)) {
      var root = mapper.readTree(response);
      root.path("stats");
      return root;
    }
    return null;
  }

  public void getPlayers(short season, Short week) {
    var url = getPlayerInfoRoute(season);
    log.debug("Getting players for the {} season from url: {}", season, url);
    var response = restTemplate.getForObject(url, Map.class);
    if (nonNull(response)) {
      var playersCollObject = response.get("players");
      var playersCollection = mapper.convertValue(playersCollObject, Collection.class);
      log.debug("Sending off player collection to sorted: {}", playersCollection.toString());
      sortPlayers(playersCollection, season, week);
    }
  }

  private void sortPlayers(Collection<Map> playerMaps, short season, Short week) {
    log.debug("sorting playerMaps: {}", playerMaps.toString());
    playerMaps.stream().forEach( mapOfPlayer -> playersService.savePlayerFromMap(mapOfPlayer, season));
    getStats(season, week);
  }

  private void sortStats(Collection<Map> statMaps, short season, Short week) {
    statMaps.stream().forEach( stat -> statService.)
  }
}
