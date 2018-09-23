package com.finalfantasy.football.stats;

public enum StatType {

  SEASON_STAT("SEASON_STAT");

  private String type;

  StatType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return type;
  }
}
