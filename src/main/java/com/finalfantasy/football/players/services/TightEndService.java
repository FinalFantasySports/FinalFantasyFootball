package com.finalfantasy.football.players.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.finalfantasy.football.players.models.DefaultPlayer;
import com.finalfantasy.football.players.models.Quarterback;
import com.finalfantasy.football.players.models.RunningBack;
import com.finalfantasy.football.players.models.TightEnd;
import com.finalfantasy.football.players.repositories.TightEndRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TightEndService {

  private final TightEndRepository repository;

  public TightEndService(final TightEndRepository repository) {
    this.repository = repository;
  }

  public Collection<TightEnd> getTightEnds() {
    return repository.findAll();
  }

  public void saveTightEndAsDefaultPlayer(DefaultPlayer player) {
    repository.save(player.toTightEnd());
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
