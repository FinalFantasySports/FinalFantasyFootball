package com.finalfantasy.football.players.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.finalfantasy.football.league.LeagueScoring;
import com.finalfantasy.football.league.LeagueService;
import com.finalfantasy.football.players.models.WideReceiver;
import com.finalfantasy.football.players.repositories.WideReceiverRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class WideReceiverService {

  private static final Logger log = LoggerFactory.getLogger(WideReceiverService.class);

  private final WideReceiverRepository repository;
  private final LeagueService leagueService;

  public WideReceiverService(final WideReceiverRepository repository, final LeagueService leagueService) {
    this.repository = repository;
    this.leagueService = leagueService;
  }

  public Collection<WideReceiver> getWideReceivers() {
    return repository.findAll();
  }

  public Collection<WideReceiver> getWideReceiversBySeasonAndOrWeek(Short season, Short week) throws Exception {

    Collection<WideReceiver> wideReceivers;
    LeagueScoring leagueScoring = leagueService.getLeague();

    if(season != null && week != null) {
      wideReceivers =  repository.findAllBySeasonAndWeek(season, week);
    } else if (season != null) {
      wideReceivers = repository.findAllBySeason(season);
    } else {
      wideReceivers = repository.findAll();
    }

    wideReceivers.parallelStream().forEach(wr -> {
      wr.fantasyPoints = leagueScoring.calculateFantasyPts(wr);
    });

    return leagueScoring.setValueBasedScore(new ArrayList(wideReceivers));
  }

  @Async
  public void saveWideReceiverWithStats(WideReceiver player, JsonNode stats) {
    try {
      player.addStats(stats);
    } catch (IOException e) {
      log.error("Unable to parse stats for {}", player.name);
      e.printStackTrace();
    }
    repository.save(player);
  }

  public void insertWideReceiverAsJsonNode(JsonNode node) {
    WideReceiver wideReceiver = new WideReceiver();
    wideReceiver.esbid = node.path("esbid").toString().replaceAll("\"", "");
    wideReceiver.gsisPlayerId = node.path("gsisPlayerId").toString().replaceAll("\"", "");
    wideReceiver.name = node.path("name").toString().replaceAll("\"", "");
    wideReceiver.teamAbbr = node.path("teamAbbr").toString().replaceAll("\"", "");
    wideReceiver.apiSeasonPts = node.path("seasonPts").floatValue();
    wideReceiver.apiSeasonProjectedPts = node.path("seasonProjectedPts").floatValue();
    wideReceiver.apiWeekPts = node.path("weekPts").floatValue();
    wideReceiver.apiWeekProjectedPts = node.path("weekProjectedPts").floatValue();

    repository.save(wideReceiver);
  }
}
