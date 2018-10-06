package com.finalfantasy.football.players.models;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public interface Player {

  void addStats(JsonNode stats) throws IOException;
}
