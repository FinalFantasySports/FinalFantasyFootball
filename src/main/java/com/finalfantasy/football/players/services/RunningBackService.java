package com.finalfantasy.football.players.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.finalfantasy.football.players.models.RunningBack;
import com.finalfantasy.football.players.repositories.RunningBackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;

@Service
public class RunningBackService {

  private static final Logger log = LoggerFactory.getLogger(RunningBackService.class);

  private final RunningBackRepository repository;

  public RunningBackService(final RunningBackRepository repository) {
    this.repository = repository;
  }

  public Collection<RunningBack> getRunningBacks() {
    return repository.findAll();
  }

  @Async
  public void saveRunningBackWithStats(RunningBack player, JsonNode stats) {
    try {
      player.addStats(stats);
    } catch (IOException e) {
      log.error("Unable to parse stats for {}", player.name);
      e.printStackTrace();
    }
    repository.save(player);
  }

  public void insertRunningBackAsJsonNode(JsonNode node) {
    RunningBack runningBack = new RunningBack();
    runningBack.esbid = node.path("esbid").toString().replaceAll("\"", "");
    runningBack.gsisPlayerId = node.path("gsisPlayerId").toString().replaceAll("\"", "");
    runningBack.name = node.path("name").toString().replaceAll("\"", "");
    runningBack.teamAbbr = node.path("teamAbbr").toString().replaceAll("\"", "");
    runningBack.apiSeasonPts = node.path("seasonPts").floatValue();
    runningBack.apiSeasonProjectedPts = node.path("seasonProjectedPts").floatValue();
    runningBack.apiWeekPts = node.path("weekPts").floatValue();
    runningBack.apiWeekProjectedPts = node.path("weekProjectedPts").floatValue();

    repository.save(runningBack);
  }
}
