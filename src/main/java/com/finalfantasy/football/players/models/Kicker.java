package com.finalfantasy.football.players.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Kicker extends AbstractPlayer implements Player {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

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
