package com.finalfantasy.football.stats;

import com.finalfantasy.football.players.models.Player;

import java.util.Comparator;

public class SortedByVBD implements Comparator<Player> {
  @Override
  public int compare(Player o1, Player o2) {
    return Math.round(o2.getValueBasedDraftScore() - o1.getValueBasedDraftScore());
  }
}
