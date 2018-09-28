package com.finalfantasy.football.players.models;

import javax.persistence.*;

@Entity
public class Quarterback extends AbstractOffensivePlayer implements Player {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  public Float passingYds;
  public Float passingTds;
  public Float passingInt;
  public Float sacked;

  public Quarterback() {
    super(Position.QB);
  }

  public Quarterback(DefaultPlayer defaultPlayer) {
    super(Position.QB);
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
