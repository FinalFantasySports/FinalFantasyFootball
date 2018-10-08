package com.finalfantasy.football.league;

import com.finalfantasy.football.players.models.Player;

import java.util.Collection;
import java.util.List;

public interface LeagueScoring {

  float calculateFantasyPts(Player player);

  Collection<Player> setValueBasedScore(List<Player> players) throws Exception;
}
