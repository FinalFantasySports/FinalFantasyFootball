package com.finalfantasy.football.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalfantasy.football.players.PlayersService;
import com.finalfantasy.football.stats.StatKeyService;
import com.finalfantasy.football.stats.StatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import static com.finalfantasy.football.NflFantasyApiConfiguration.*;
import static java.util.Objects.nonNull;

@Service
public class ApiService {

  private static final Logger log = LoggerFactory.getLogger(ApiService.class);

  private final RestTemplate restTemplate;
  private final PlayersService playersService;
  private final StatService statService;
  private final StatKeyService statKeyService;
  private final ObjectMapper mapper;

  public ApiService(final PlayersService playersService, final StatService statService,
      final StatKeyService statKeyService) {
    this.restTemplate = new RestTemplate();
    this.playersService = playersService;
    this.statService = statService;
    this.statKeyService = statKeyService;
    mapper = new ObjectMapper();
  }

  @Async
  public void getStats(int season, int week) {
    var url = getStatsFor(season, week);
    var response = restTemplate.getForObject(url, Map.class);
    if (nonNull(response)) {
      var statsCollObject = response.get("players");
      var statsCollection = mapper.convertValue(statsCollObject, Collection.class);
      sortStats(statsCollection, season, week);
    }
  }

  @PostConstruct
  public void populateStatKey() throws IOException {
    var statKeyNodes = getStatKeys();
    if (nonNull(statKeyNodes)) {
      statKeyNodes.iterator().forEachRemaining(statKeyNode ->statKeyService.saveStatKeyFromJsonNode(statKeyNode));
    }
  }

  private JsonNode getStatKeys() throws IOException {
    var url = getStatsKeyRoute();
    log.debug("url: {}", url);
    var response = restTemplate.getForObject(url, String.class);
    log.debug("response: {}", response);
    if (nonNull(response)) {
      var root = mapper.readTree(response);
      return root.path("stats");
    }
    return null;
  }

  @Async
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
    playerMaps.parallelStream().forEach( mapOfPlayer -> playersService.savePlayerFromMap(mapOfPlayer, season));
    getStats(season, week);
  }

  private void sortStats(Collection<Map> statMaps, int season, int week) {
    statMaps.parallelStream().forEach( stat -> statService.saveStatsFromMap(stat, season, week));
  }
}
