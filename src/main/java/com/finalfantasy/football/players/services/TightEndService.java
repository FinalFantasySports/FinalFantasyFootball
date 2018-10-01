package com.finalfantasy.football.players.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.finalfantasy.football.players.models.TightEnd;
import com.finalfantasy.football.players.repositories.TightEndRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;

@Service
public class TightEndService {

  private static final Logger log = LoggerFactory.getLogger(TightEndService.class);

  private final TightEndRepository repository;

  public TightEndService(final TightEndRepository repository) {
    this.repository = repository;
  }

  public Collection<TightEnd> getTightEnds() {
    return repository.findAll();
  }

  public Collection<TightEnd> getTightEndsBySeasonAndOrWeek(Short season, Short week) {

    if(season != null && week != null) {
      return repository.findAllBySeasonAndWeek(season, week);
    } else if (season != null) {
      return repository.findAllBySeason(season);
    } else {
      return repository.findAll();
    }
  }

  @Async
  public void saveTightEndWithStats(TightEnd player, JsonNode stats) {
    try {
      player.addStats(stats);
    } catch (IOException e) {
      log.error("Unable to parse stats for {}", player.name);
      e.printStackTrace();
    }
    repository.save(player);
  }

  public void insertTightEndAsJsonNode(JsonNode node) {
    TightEnd tightEnd = new TightEnd();
    tightEnd.esbid = node.path("esbid").toString().replaceAll("\"", "");
    tightEnd.gsisPlayerId = node.path("gsisPlayerId").toString().replaceAll("\"", "");
    tightEnd.name = node.path("name").toString().replaceAll("\"", "");
    tightEnd.teamAbbr = node.path("teamAbbr").toString().replaceAll("\"", "");
    tightEnd.apiSeasonPts = node.path("seasonPts").floatValue();
    tightEnd.apiSeasonProjectedPts = node.path("seasonProjectedPts").floatValue();
    tightEnd.apiWeekPts = node.path("weekPts").floatValue();
    tightEnd.apiWeekProjectedPts = node.path("weekProjectedPts").floatValue();

    repository.save(tightEnd);
  }
}
