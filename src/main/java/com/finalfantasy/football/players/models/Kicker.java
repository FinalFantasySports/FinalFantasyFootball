package com.finalfantasy.football.players.models;

import javax.persistence.*;

@Entity
public class Kicker extends AbstractPlayer implements Player {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  Short extraPoint;
  Short fieldGoalsUnder40;
  Short fieldGoals40_49;
  Short fieldGoal50orOver;

  public Kicker() {
    super(Position.K);
  }

  public Kicker(DefaultPlayer defaultPlayer) {
    super(Position.K);
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
