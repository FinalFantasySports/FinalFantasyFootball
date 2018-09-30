package com.finalfantasy.football.players.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.finalfantasy.football.players.models.Quarterback;
import com.finalfantasy.football.players.repositories.QuarterbackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;

@Service
public class QuarterbackService {

  private static final Logger log = LoggerFactory.getLogger(QuarterbackService.class);

  private final QuarterbackRepository repository;

  public QuarterbackService(final QuarterbackRepository repository) {
    this.repository = repository;
  }

  public Collection<Quarterback> getQuarterbacks() {
    return repository.findAll();
  }

  @Async
  public void saveQuarterbackWithStats(Quarterback player, JsonNode stats) {
    try {
      player.addStats(stats);
    } catch (IOException e) {
      log.error("Unable to parse stats for {}", player.name);
      e.printStackTrace();
    }
    repository.save(player);
  }

  public void insertQuarterbackAsJsonNode(JsonNode node) {
    Quarterback quarterback = new Quarterback();
    quarterback.esbid = node.path("esbid").toString().replaceAll("\"", "");
    quarterback.gsisPlayerId = node.path("gsisPlayerId").toString().replaceAll("\"", "");
    quarterback.name = node.path("name").toString().replaceAll("\"", "");
    quarterback.teamAbbr = node.path("teamAbbr").toString().replaceAll("\"", "");
    quarterback.apiSeasonPts = node.path("seasonPts").floatValue();
    quarterback.apiSeasonProjectedPts = node.path("seasonProjectedPts").floatValue();
    quarterback.apiWeekPts = node.path("weekPts").floatValue();
    quarterback.apiWeekProjectedPts = node.path("weekProjectedPts").floatValue();

    repository.save(quarterback);
  }
}
