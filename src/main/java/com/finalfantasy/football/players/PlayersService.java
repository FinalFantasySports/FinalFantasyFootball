package com.finalfantasy.football.players;

import com.fasterxml.jackson.databind.JsonNode;
import com.finalfantasy.football.players.models.*;
import com.finalfantasy.football.players.repositories.PlayerRepository;
import com.finalfantasy.football.players.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PlayersService {

  private static final Logger log = LoggerFactory.getLogger(PlayersService.class);

  private final QuarterbackService quarterbackService;
  private final RunningBackService runningBackService;
  private final WideReceiverService wideReceiverService;
  private final TightEndService tightEndService;
  private final KickerService kickerService;
  private final DefenseSpecialTeamsService defenseSpecialTeamsService;
  private final PlayerRepository playerRepository;

  public PlayersService(final QuarterbackService quarterbackService,
    final RunningBackService runningBackService, final WideReceiverService wideReceiverService,
    final KickerService kickerService, final TightEndService tightEndService,
    final DefenseSpecialTeamsService defenseSpecialTeamsService, final PlayerRepository playerRepository) {
    this.quarterbackService = quarterbackService;
    this.runningBackService = runningBackService;
    this.wideReceiverService = wideReceiverService;
    this.tightEndService = tightEndService;
    this.kickerService = kickerService;
    this.defenseSpecialTeamsService = defenseSpecialTeamsService;
    this.playerRepository = playerRepository;
  }

  public void sortPlayer(JsonNode player) {
    String position = player.path("position").toString().replaceAll("\"", "");
    log.debug("position: {}", position);
    switch (position) {
      case "QB" :
        quarterbackService.insertQuarterbackAsJsonNode(player);
        break;
      case "RB" :
        runningBackService.insertRunningBackAsJsonNode(player);
        break;
      case "WR" :
        wideReceiverService.insertWideReceiverAsJsonNode(player);
        break;
      case "TE" :
        tightEndService.insertTightEndAsJsonNode(player);
        break;
      case "K" :
        kickerService.insertKickerAsJsonNode(player);
        break;
      case "DEF" :
        defenseSpecialTeamsService.insertDefenseSpecialTeamsAsJsonNode(player);
        break;
      default:
        insertPlayerAsJsonNode(player);
        break;
    }
  }

  private void insertPlayerAsJsonNode(JsonNode node) {
    MiscPlayer player = new MiscPlayer(node.path("miscPosition").toString());
    player.esbid = node.path("esbid").toString();
    player.gsisPlayerId = node.path("gsisPlayerId").toString();
    player.name = node.path("name").toString().replaceAll("\"", "");
    player.teamAbbr = node.path("teamAbbr").toString().replaceAll("\"", "");
    player.apiSeasonPts = node.path("apiSeasonPts").floatValue();
    player.apiSeasonProjectedPts = node.path("apiSeasonProjectedPts").floatValue();
    player.apiWeekPts = node.path("apiWeekPts").floatValue();
    player.apiWeekProjectedPts = node.path("apiWeekProjectedPts").floatValue();

    playerRepository.save(player);
  }


}
