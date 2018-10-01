package com.finalfantasy.football.players.models;

import javax.persistence.*;

@Entity(name = "widereceivers")
@Table(indexes = { @Index(name = "IDX_SEASON_WEEK", columnList = "season,week") })
public class WideReceiver extends AbstractOffensivePlayer implements Player {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  public WideReceiver() {
    super(Position.WR);
  }

  public WideReceiver(DefaultPlayer defaultPlayer) {
    super(Position.WR);
    this.esbid = defaultPlayer.esbid;
    this.gsisPlayerId = defaultPlayer.gsisPlayerId;
    this.name = defaultPlayer.name;
    this.teamAbbr = defaultPlayer.teamAbbr;
    this.apiSeasonPts = defaultPlayer.apiSeasonPts;
    this.apiSeasonProjectedPts = defaultPlayer.apiSeasonProjectedPts;
    this.apiWeekPts = defaultPlayer.apiWeekPts;
    this.apiWeekProjectedPts = defaultPlayer.apiWeekProjectedPts;
    this.season = defaultPlayer.season;
    this.week = defaultPlayer.week;
  }
}
