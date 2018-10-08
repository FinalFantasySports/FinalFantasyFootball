package com.finalfantasy.football.players.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.finalfantasy.football.league.LeagueScoring;
import com.finalfantasy.football.league.LeagueService;
import com.finalfantasy.football.players.models.Kicker;
import com.finalfantasy.football.players.repositories.KickerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class KickerService {

  private static final Logger log = LoggerFactory.getLogger(WideReceiverService.class);

  private final KickerRepository repository;
  private final LeagueService leagueService;

  public KickerService(final KickerRepository repository, final LeagueService leagueService) {
    this.repository = repository;
    this.leagueService = leagueService;
  }

  public Collection<Kicker> getKickers() {
    return repository.findAll();
  }

  public Collection<Kicker> getKickersBySeasonAndOrWeek(Short season, Short week) throws Exception {

    Collection<Kicker> kickers;
    LeagueScoring leagueScoring = leagueService.getLeague();

    if(season != null && week != null) {
      kickers =  repository.findAllBySeasonAndWeek(season, week);
    } else if (season != null) {
      kickers = repository.findAllBySeason(season);
    } else {
      kickers = repository.findAll();
    }

    kickers.parallelStream().forEach(kicker -> {
      kicker.fantasyPoints = leagueScoring.calculateFantasyPts(kicker);
    });

    return leagueScoring.setValueBasedScore(new ArrayList(kickers));
  }

  @Async
  public void saveKickerWithStats(Kicker player, JsonNode stats) {
    try {
      player.addStats(stats);
    } catch (IOException e) {
      log.error("Unable to parse stats for {}", player.name);
      e.printStackTrace();
    }
    repository.save(player);
  }

  public void insertKickerAsJsonNode(JsonNode node) {
    Kicker kicker = new Kicker();
    kicker.esbid = node.path("esbid").toString().replaceAll("\"", "");
    kicker.gsisPlayerId = node.path("gsisPlayerId").toString().replaceAll("\"", "");
    kicker.name = node.path("name").toString().replaceAll("\"", "");
    kicker.teamAbbr = node.path("teamAbbr").toString().replaceAll("\"", "");
    kicker.apiSeasonPts = node.path("seasonPts").floatValue();
    kicker.apiSeasonProjectedPts = node.path("seasonProjectedPts").floatValue();
    kicker.apiWeekPts = node.path("weekPts").floatValue();
    kicker.apiWeekProjectedPts = node.path("weekProjectedPts").floatValue();

    repository.save(kicker);
  }
}
