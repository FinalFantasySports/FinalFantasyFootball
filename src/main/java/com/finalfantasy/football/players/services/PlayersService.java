package com.finalfantasy.football.players.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.finalfantasy.football.players.models.*;
import com.finalfantasy.football.players.repositories.PlayerRepository;
import com.finalfantasy.football.stats.SortedByVBD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

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
  private final ExecutorService executorService;

  public PlayersService(final QuarterbackService quarterbackService,
    final RunningBackService runningBackService, final WideReceiverService wideReceiverService,
    final KickerService kickerService, final TightEndService tightEndService,
    final DefenseSpecialTeamsService defenseSpecialTeamsService, final PlayerRepository playerRepository, final ExecutorService executorService) {
    this.quarterbackService = quarterbackService;
    this.runningBackService = runningBackService;
    this.wideReceiverService = wideReceiverService;
    this.tightEndService = tightEndService;
    this.kickerService = kickerService;
    this.defenseSpecialTeamsService = defenseSpecialTeamsService;
    this.playerRepository = playerRepository;
    this.executorService = executorService;
  }

  public Collection<Player> getAllPlayers() throws Exception {

    Future<Collection<Quarterback>> futureQuarterbacks = executorService.submit(() -> {
      return quarterbackService.getQuarterbacksBySeasonAndOrWeek(null,null );
    });
    Future<Collection<RunningBack>> futureRunningBacks = executorService.submit(() -> {
      return runningBackService.getRunningBacksBySeasonAndOrWeek(null,null );
    });
    Future<Collection<WideReceiver>> futureWideReceivers = executorService.submit(() -> {
      return wideReceiverService.getWideReceiversBySeasonAndOrWeek(null,null );
    });
    Future<Collection<TightEnd>> futureTightEnds = executorService.submit(() -> {
      return tightEndService.getTightEndsBySeasonAndOrWeek(null,null );
    });
    Future<Collection<Kicker>> futureKickers = executorService.submit(() -> {
      return kickerService.getKickersBySeasonAndOrWeek(null,null );
    });
    Future<Collection<DefenseSpecialTeams>> futureDefenseSpecialTeamss = executorService.submit(() -> {
      return defenseSpecialTeamsService.getDefenseSpecialTeamssBySeasonAndOrWeek(null,null );
    });

    while(!(futureQuarterbacks.isDone() && futureRunningBacks.isDone() && futureDefenseSpecialTeamss.isDone() &&
        futureKickers.isDone() && futureTightEnds.isDone() && futureWideReceivers.isDone())) {
      log.debug("Getting Players...");
    }

    Collection<Player> players = new ArrayList<>(futureQuarterbacks.get());
    players.addAll(futureRunningBacks.get());
    players.addAll(futureWideReceivers.get());
    players.addAll(futureTightEnds.get());
    players.addAll(futureKickers.get());
    players.addAll(futureDefenseSpecialTeamss.get());

    return players.stream().sorted(new SortedByVBD()).collect(Collectors.toList());
  }

  @Async
  public void sortPlayer(JsonNode node, short week, short season) {

    DefaultPlayer player = jsonToDefaultPlayer(node);
    player.week = week;
    player.season = season;
    switch (player.position) {
      case QB:
        quarterbackService.saveQuarterbackWithStats(player.toQuarterback(), player.stats);
        break;
      case RB :
        runningBackService.saveRunningBackWithStats(player.toRunningBack(), player.stats);
        break;
      case WR :
        wideReceiverService.saveWideReceiverWithStats(player.toWideReceiver(), player.stats);
        break;
      case TE :
        tightEndService.saveTightEndWithStats(player.toTightEnd(), player.stats);
        break;
      case K :
        kickerService.saveKickerWithStats(player.toKicker(), player.stats);
        break;
      case DEF :
        defenseSpecialTeamsService.saveDefenseSpecialTeamsWithStats(player.toDefenseSpecialTeams(), player.stats);
        break;
      default:
        insertPlayer(player);
        break;
    }
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
    defaultPlayer.stats = player.path("stats");

    return defaultPlayer;
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
