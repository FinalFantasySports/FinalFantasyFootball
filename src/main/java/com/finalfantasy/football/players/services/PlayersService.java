package com.finalfantasy.football.players.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalfantasy.football.players.models.*;
import com.finalfantasy.football.players.repositories.PlayerRepository;
import com.finalfantasy.football.stats.StatKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

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
  private final ArrayList<StatKey> statKeys;

  public PlayersService(final QuarterbackService quarterbackService,
    final RunningBackService runningBackService, final WideReceiverService wideReceiverService,
    final KickerService kickerService, final TightEndService tightEndService,
    final DefenseSpecialTeamsService defenseSpecialTeamsService, final PlayerRepository playerRepository, final
                        ArrayList<StatKey> statKeys) {
    this.quarterbackService = quarterbackService;
    this.runningBackService = runningBackService;
    this.wideReceiverService = wideReceiverService;
    this.tightEndService = tightEndService;
    this.kickerService = kickerService;
    this.defenseSpecialTeamsService = defenseSpecialTeamsService;
    this.playerRepository = playerRepository;
    this.statKeys = statKeys;
  }

  public void sortPlayer(JsonNode node) {

    DefaultPlayer player = jsonToDefaultPlayer(node);

    switch (player.position) {
      case QB:
        quarterbackService.saveQuarterbackAsDefaultPlayer(player);
        break;
      case RB :
        runningBackService.saveRunningBackAsDefaultPlayer(player);
        break;
      case WR :
        wideReceiverService.saveWideReceiverAsDefaultPlayer(player);
        break;
      case TE :
        tightEndService.saveTightEndAsDefaultPlayer(player);
        break;
      case K :
        kickerService.saveKickerAsDefaultPlayer(player);
        break;
      case DEF :
        defenseSpecialTeamsService.saveDefenseSpecialTeamsAsDefaultPlayer(player);
        break;
      default:
        insertPlayer(player);
        break;
    }
  }

  private DefaultPlayer setStats(DefaultPlayer player, JsonNode statsNode) {

    var objectMapper = new ObjectMapper();

    statKeys.stream().forEach(statKey -> {
      var map = objectMapper.convertValue(statsNode,HashMap.class);
      map.forEach((key, value) -> {
        if(Integer.valueOf(key.toString()) == statKey.id) {
          player.stats.put(statKey.abbr, Float.valueOf(value.toString()));
        }
      });
    });

    return player;
  }

  private DefaultPlayer jsonToDefaultPlayer(JsonNode player) {

    DefaultPlayer defaultPlayer = new DefaultPlayer(player.path("position").toString().replaceAll("\"", ""));
    defaultPlayer.esbid = player.path("esbid").toString().replaceAll("\"", "");
    defaultPlayer.gsisPlayerId = player.path("gsisPlayerId").toString().replaceAll("\"", "");
    defaultPlayer.name = player.path("name").toString().replaceAll("\"", "");
    defaultPlayer.teamAbbr = player.path("teamAbbr").toString().replaceAll("\"", "");
    defaultPlayer.apiSeasonPts = player.path("seasonPts").floatValue();
    defaultPlayer.apiSeasonProjectedPts = player.path("seasonProjectedPts").floatValue();
    defaultPlayer.apiWeekPts = player.path("weekPts").floatValue();
    defaultPlayer.apiWeekProjectedPts = player.path("weekProjectedPts").floatValue();

    return setStats(defaultPlayer, player.path("stats"));
  }

  private void insertPlayer(DefaultPlayer player) {
    playerRepository.save(player);
  }

  private void insertPlayerAsJsonNode(JsonNode node) {
    DefaultPlayer player = new DefaultPlayer(node.path("miscPosition").toString());
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
