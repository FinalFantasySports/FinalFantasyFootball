package com.finalfantasy.football;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalfantasy.football.stats.StatKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;

@Configuration
public class FantasyApiConfiguration {

  private static final Logger log = LoggerFactory.getLogger(FantasyApiConfiguration.class);

  private final static String apiDomain = "http://api.fantasy.nfl.com";
  private final static String gameRoute = "/v1/game";
  private final static String statsRoute = "/stats";
  private static final String playersRoute = "/v1/players";
  private static final String query = "?";
  private static final String and = "&";
  private static final String statTypeQuery = "statType=";
  private static final String seasonQuery = "season=";
  private static final String weekQuery = "week=";
  private final static String jsonFormat = "format=json";


  @Bean
  public ArrayList<StatKey> statKeys() throws IOException {
    RestTemplate restTemplate = new RestTemplate();
    var response = restTemplate.getForObject(getStatKeyRoute(),String.class);

    var objectMapper = new ObjectMapper();
    var root = objectMapper.readTree(response);
    ArrayList<StatKey> statKeys = new ArrayList<>();
    root.path("stats").iterator().forEachRemaining(statKeyNode -> {
      var statKey = new StatKey();
      statKeys.add(statKey);
      statKey.id = statKeyNode.path("id").asInt();
      statKey.abbr = statKeyNode.path("abbr").textValue();
      statKey.name = statKeyNode.path("name").textValue();
      statKey.shortName = statKeyNode.path("shortName").textValue();
    });
    return statKeys;
  }

  // url = http://api.fantasy.nfl.com/v1/game/stats?format=json
  public String getStatKeyRoute() {
    StringBuilder stringBuilder = new StringBuilder(apiDomain);
    stringBuilder.append(gameRoute);
    stringBuilder.append(statsRoute);
    stringBuilder.append(query);
    stringBuilder.append(jsonFormat);
    return stringBuilder.toString();
  }

  // url = http://api.fantasy.nfl.com/v1/players/stats?statType=seasonStats&season=2018&week=1&format=json
  public String getPlayerStatsByWeekRoute(short season, short week) {
    StringBuilder stringBuilder = new StringBuilder(apiDomain);
    stringBuilder.append(playersRoute);
    stringBuilder.append(statsRoute);
    stringBuilder.append(query);
    stringBuilder.append(statTypeQuery);
    stringBuilder.append("weekStats");
    stringBuilder.append(and);
    stringBuilder.append(seasonQuery);
    log.debug("weekData.season {}", season);
    stringBuilder.append(season);
    stringBuilder.append(and);
    stringBuilder.append(weekQuery);
    log.debug("weekData.week {}", week);
    stringBuilder.append(week);
    stringBuilder.append(and);
    stringBuilder.append(jsonFormat);
    return stringBuilder.toString();
  }
}
