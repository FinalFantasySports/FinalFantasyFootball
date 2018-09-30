package com.finalfantasy.football.players.models;

public enum Position {
  QB("QB"),
  RB("RB"),
  WR("WR"),
  TE("TE"),
  K("K"),
  DEF("DEF"),
  DL("DL"),
  LB("LB"),
  DB("DB");

  public String name;

  Position(String name) {
    this.name = name;
  }

}
