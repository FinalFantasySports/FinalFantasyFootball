package com.finalfantasy.football.players.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.finalfantasy.football.players.models.DefenseSpecialTeams;
import com.finalfantasy.football.players.repositories.DefenseSpecialTeamsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;

@Service
public class DefenseSpecialTeamsService {

  private static final Logger log = LoggerFactory.getLogger(DefenseSpecialTeamsService.class);

  private final DefenseSpecialTeamsRepository repository;

  public DefenseSpecialTeamsService(final DefenseSpecialTeamsRepository repository) {
    this.repository = repository;
  }

  public Collection<DefenseSpecialTeams> getDefenseSpecialTeamss() {
    return repository.findAll();
  }

  @Async
  public void saveDefenseSpecialTeamsWithStats(DefenseSpecialTeams player, JsonNode stats) {
    try {
      player.addStats(stats);
    } catch (IOException e) {
      log.error("Unable to parse stats for {}", player.name);
      e.printStackTrace();
    }
    repository.save(player);
  }

  public void insertDefenseSpecialTeamsAsJsonNode(JsonNode node) {
    DefenseSpecialTeams defenseSpecialTeams = new DefenseSpecialTeams();
    defenseSpecialTeams.esbid = node.path("esbid").toString().replaceAll("\"", "");
    defenseSpecialTeams.gsisPlayerId = node.path("gsisPlayerId").toString().replaceAll("\"", "");
    defenseSpecialTeams.name = node.path("name").toString().replaceAll("\"", "");
    defenseSpecialTeams.teamAbbr = node.path("teamAbbr").toString().replaceAll("\"", "");
    defenseSpecialTeams.apiSeasonPts = node.path("seasonPts").floatValue();
    defenseSpecialTeams.apiSeasonProjectedPts = node.path("seasonProjectedPts").floatValue();
    defenseSpecialTeams.apiWeekPts = node.path("weekPts").floatValue();
    defenseSpecialTeams.apiWeekProjectedPts = node.path("weekProjectedPts").floatValue();

    repository.save(defenseSpecialTeams);
  }


}
