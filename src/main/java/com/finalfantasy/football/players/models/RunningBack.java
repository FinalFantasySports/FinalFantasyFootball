package com.finalfantasy.football.players.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RunningBack extends AbstractOffensivePlayer implements Player {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  public RunningBack() {
    super(Position.RB);
  }

  public RunningBack(DefaultPlayer defaultPlayer) {
    super(Position.RB);
    this.esbid = defaultPlayer.esbid;
    this.gsisPlayerId = defaultPlayer.gsisPlayerId;
    this.name = defaultPlayer.name;
    this.teamAbbr = defaultPlayer.teamAbbr;
    this.apiSeasonPts = defaultPlayer.apiSeasonPts;
    this.apiSeasonProjectedPts = defaultPlayer.apiSeasonProjectedPts;
    this.apiWeekPts = defaultPlayer.apiWeekPts;
    this.apiWeekProjectedPts = defaultPlayer.apiWeekProjectedPts;
  }
}
