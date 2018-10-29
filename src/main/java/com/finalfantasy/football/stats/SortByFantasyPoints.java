package com.finalfantasy.football.stats;

import com.finalfantasy.football.players.models.Player;

import java.util.Comparator;

public class SortByFantasyPoints implements Comparator<Player> {
  @Override
  public int compare(Player o1, Player o2) {
//    return o2.getFantasyPointsAsInt() - o1.getFantasyPointsAsInt();
    return 0;
  }
}
