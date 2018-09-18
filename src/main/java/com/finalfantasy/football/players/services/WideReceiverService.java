package com.finalfantasy.football.players.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.finalfantasy.football.players.models.WideReceiver;
import com.finalfantasy.football.players.repositories.WideReceiverRepository;
import org.springframework.stereotype.Service;

@Service
public class WideReceiverService {

  private final WideReceiverRepository repository;

  public WideReceiverService(final WideReceiverRepository repository) {
    this.repository = repository;
  }

  public void insertWideReceiverAsJsonNode(JsonNode node) {
    WideReceiver wideReceiver = new WideReceiver();
    wideReceiver.esbid = node.path("esbid").toString().replaceAll("\"", "");
    wideReceiver.gsisPlayerId = node.path("gsisPlayerId").toString().replaceAll("\"", "");
    wideReceiver.name = node.path("name").toString().replaceAll("\"", "");
    wideReceiver.teamAbbr = node.path("teamAbbr").toString().replaceAll("\"", "");
    wideReceiver.apiSeasonPts = node.path("apiSeasonPts").floatValue();
    wideReceiver.apiSeasonProjectedPts = node.path("apiSeasonProjectedPts").floatValue();
    wideReceiver.apiWeekPts = node.path("apiWeekPts").floatValue();
    wideReceiver.apiWeekProjectedPts = node.path("apiWeekProjectedPts").floatValue();

    repository.save(wideReceiver);
  }
}
