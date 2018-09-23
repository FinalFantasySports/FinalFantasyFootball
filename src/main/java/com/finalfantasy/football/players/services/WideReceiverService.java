package com.finalfantasy.football.players.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.finalfantasy.football.players.models.DefaultPlayer;
import com.finalfantasy.football.players.models.Quarterback;
import com.finalfantasy.football.players.models.RunningBack;
import com.finalfantasy.football.players.models.WideReceiver;
import com.finalfantasy.football.players.repositories.WideReceiverRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class WideReceiverService {

  private final WideReceiverRepository repository;

  public WideReceiverService(final WideReceiverRepository repository) {
    this.repository = repository;
  }

  public Collection<WideReceiver> getWideReceivers() {
    return repository.findAll();
  }

  public void saveWideReceiverAsDefaultPlayer(DefaultPlayer player) {
    repository.save(player.toWideReceiver());
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
