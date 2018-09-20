package com.finalfantasy.football.players.services;

import com.fasterxml.jackson.databind.JsonNode;
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

  public void insertTightEndAsJsonNode(JsonNode node) {
    TightEnd tightEnd = new TightEnd();
    tightEnd.esbid = node.path("esbid").toString().replaceAll("\"", "");
    tightEnd.gsisPlayerId = node.path("gsisPlayerId").toString().replaceAll("\"", "");
    tightEnd.name = node.path("name").toString().replaceAll("\"", "");
    tightEnd.teamAbbr = node.path("teamAbbr").toString().replaceAll("\"", "");
    tightEnd.apiSeasonPts = node.path("apiSeasonPts").floatValue();
    tightEnd.apiSeasonProjectedPts = node.path("apiSeasonProjectedPts").floatValue();
    tightEnd.apiWeekPts = node.path("apiWeekPts").floatValue();
    tightEnd.apiWeekProjectedPts = node.path("apiWeekProjectedPts").floatValue();

    repository.save(tightEnd);
  }
}
