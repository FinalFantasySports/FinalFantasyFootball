package com.finalfantasy.football.players.models;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public interface Player {

  int getFantasyPointsAsInt();
  float getFantasyPoints();
  void addStats(JsonNode stats) throws IOException;
  void setValueBasedDraftScore(float vbd);
  float getValueBasedDraftScore();
}
