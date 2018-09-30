package com.finalfantasy.football.stats;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalfantasy.football.FantasyApiConfiguration;
import com.finalfantasy.football.players.services.PlayersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class StatsService {

  private static final Logger log = LoggerFactory.getLogger(StatsService.class);

  private final RestTemplate restTemplate;
  private final PlayersService playersService;
  private final FantasyApiConfiguration fantasyApiConfiguration;
  private final StatKeyRepository statKeyRepository;

  public StatsService(final RestTemplate restTemplate, final PlayersService playersService, final
                      FantasyApiConfiguration fantasyApiConfiguration, final StatKeyRepository statKeyRepository) {
    this.restTemplate = restTemplate;
    this.playersService = playersService;
    this.fantasyApiConfiguration = fantasyApiConfiguration;
    this.statKeyRepository = statKeyRepository;
  }

  public String populateStatKey() throws IOException {
    var response = restTemplate.getForObject(fantasyApiConfiguration.getStatKeyRoute(),String.class);

    var objectMapper = new ObjectMapper();
    var root = objectMapper.readTree(response);
    root.path("stats").iterator().forEachRemaining(statKeyNode -> {
      var statKey = new StatKey();
      statKey.id = statKeyNode.path("id").asInt();
      statKey.abbr = statKeyNode.path("abbr").textValue();
      statKey.name = statKeyNode.path("name").textValue();
      statKey.shortName = statKeyNode.path("shortName").textValue();
      statKeyRepository.save(statKey);
    });

    return null;
  }


  public boolean populateDatabaseBySeasonAndWeek(WeekData weekData) throws IOException {

    var url = fantasyApiConfiguration.getPlayerStatsByWeekRoute(weekData);
    log.debug("url={}", url);
    var response = restTemplate.getForObject(url, String.class);

    var objectMapper = new ObjectMapper();
    var root = objectMapper.readTree(response);
    var playersNode = root.path("players");

    playersNode.iterator().forEachRemaining(player -> {
      playersService.sortPlayer(player, weekData.week, weekData.season);
    });
    return false;
  }
}
