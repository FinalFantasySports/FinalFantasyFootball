package com.finalfantasy.football.players.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.finalfantasy.football.stats.YahooLeagueScoring;

import java.io.IOException;

public interface Player {

  void calculateYahooFantasyPoints(YahooLeagueScoring yahooLeagueScoring);

  void addStats(JsonNode stats) throws IOException;
}
