package com.finalfantasy.football.players.models;

import javax.persistence.*;

@Entity
public class DefenseSpecialTeams extends AbstractPlayer implements Player {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  short sacks;
  short interception;
  short fumbleRecovery;
  short touchdown;
  short safety;
  float returnYds;
  short blockKick;
  short kickOffAndPuntReturnTds;
  short pointsAllowed0;
  short pointsAllowed1_6;
  short pointsAllowed7_13;
  short pointsAllowed14_20;
  short pointsAllowed21_27;
  short pointsAllowed28_34;
  short pointsAllowedOver35;
  short fourthDownStops;
  short extraPointReturned;

  public DefenseSpecialTeams() {
    super(Position.DEF);
  }

  public DefenseSpecialTeams(DefaultPlayer defaultPlayer) {
    super(Position.DEF);
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
