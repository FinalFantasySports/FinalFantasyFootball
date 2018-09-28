package com.finalfantasy.football.players.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.finalfantasy.football.players.models.DefaultPlayer;
import com.finalfantasy.football.players.models.DefenseSpecialTeams;
import com.finalfantasy.football.players.models.Quarterback;
import com.finalfantasy.football.players.models.RunningBack;
import com.finalfantasy.football.players.repositories.DefenseSpecialTeamsRepository;
import com.finalfantasy.football.stats.StatKey;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class DefenseSpecialTeamsService {

  private final DefenseSpecialTeamsRepository repository;
  private final ArrayList<StatKey> statKeys;

  public DefenseSpecialTeamsService(final DefenseSpecialTeamsRepository repository, final ArrayList<StatKey> statKeys) {
    this.repository = repository;
    this.statKeys = statKeys;
  }

  public Collection<DefenseSpecialTeams> getDefenseSpecialTeamss() {
    return repository.findAll();
  }

  public void saveDefenseSpecialTeamsAsDefaultPlayer(DefaultPlayer player) {
    repository.save(player.toDefenseSpecialTeams());
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
