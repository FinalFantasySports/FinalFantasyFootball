package com.finalfantasy.football.players.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.finalfantasy.football.players.models.DefaultPlayer;
import com.finalfantasy.football.players.models.Quarterback;
import com.finalfantasy.football.players.repositories.QuarterbackRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class QuarterbackService {

  private final QuarterbackRepository repository;

  public QuarterbackService(final QuarterbackRepository repository) {
    this.repository = repository;
  }

  public Collection<Quarterback> getQuarterbacks() {
    return repository.findAll();
  }

  public void saveQuarterbackAsDefaultPlayer(DefaultPlayer player) {
    repository.save(player.toQuarterback());
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
