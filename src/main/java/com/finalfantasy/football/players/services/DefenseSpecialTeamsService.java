package com.finalfantasy.football.players.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.finalfantasy.football.players.models.DefenseSpecialTeams;
import com.finalfantasy.football.players.repositories.DefenseSpecialTeamsRepository;
import org.springframework.stereotype.Service;

@Service
public class DefenseSpecialTeamsService {

  private final DefenseSpecialTeamsRepository repository;

  public DefenseSpecialTeamsService(final DefenseSpecialTeamsRepository repository) {
    this.repository = repository;
  }

  public void insertDefenseSpecialTeamsAsJsonNode(JsonNode node) {
    DefenseSpecialTeams defenseSpecialTeams = new DefenseSpecialTeams();
    defenseSpecialTeams.esbid = node.path("esbid").toString().replaceAll("\"", "");
    defenseSpecialTeams.gsisPlayerId = node.path("gsisPlayerId").toString().replaceAll("\"", "");
    defenseSpecialTeams.name = node.path("name").toString().replaceAll("\"", "");
    defenseSpecialTeams.teamAbbr = node.path("teamAbbr").toString().replaceAll("\"", "");
    defenseSpecialTeams.apiSeasonPts = node.path("apiSeasonPts").floatValue();
    defenseSpecialTeams.apiSeasonProjectedPts = node.path("apiSeasonProjectedPts").floatValue();
    defenseSpecialTeams.apiWeekPts = node.path("apiWeekPts").floatValue();
    defenseSpecialTeams.apiWeekProjectedPts = node.path("apiWeekProjectedPts").floatValue();

    repository.save(defenseSpecialTeams);
  }
}
