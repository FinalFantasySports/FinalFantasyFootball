package com.finalfantasy.football.players;

public interface Player {

  public enum POSTION {
    RB,
    WR,
    QB,
    TE
  }

  Float calculateFantasyPoints();
}
