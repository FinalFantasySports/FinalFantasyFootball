package com.finalfantasy.football.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalfantasy.football.FantasyApiConfiguration;
import com.finalfantasy.football.stats.StatKey;
import com.finalfantasy.football.stats.StatKeyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;


@Service
public class ApiService {

  private static final Logger log = LoggerFactory.getLogger(ApiService.class);

  private final RestTemplate restTemplate;
  private final FantasyApiConfiguration apiConfiguration;
  private final StatKeyRepository statKeyRepository;

  public ApiService(final RestTemplate restTemplate, final FantasyApiConfiguration apiConfiguration, final StatKeyRepository statKeyRepository) {
    this.restTemplate = restTemplate;
    this.apiConfiguration = apiConfiguration;
    this.statKeyRepository = statKeyRepository;
  }

  @PostConstruct
  protected void getStatKey() throws IOException {
    var response = restTemplate.getForObject(apiConfiguration.getStatKeyRoute(),String.class);
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
  }

  public JsonNode getPlayerStatsByWeekRoute(short season, short week) throws IOException {
    var url = apiConfiguration.getPlayerStatsByWeekRoute(season, week);
    log.debug("url={}", url);
    var response = restTemplate.getForObject(url, String.class);
    var objectMapper = new ObjectMapper();
    var root = objectMapper.readTree(response);
    return root.path("players");
  }
}
