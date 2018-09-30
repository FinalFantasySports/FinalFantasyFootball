package com.finalfantasy.football.players.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.finalfantasy.football.stats.YahooLeagueScoring;

import javax.persistence.*;

@Entity(name = "players")
public class DefaultPlayer extends AbstractPlayer implements Player {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @Transient
  public JsonNode stats;

  public DefaultPlayer(String position) {
    super(Position.valueOf(position));
  }

  public RunningBack toRunningBack() {
    return new RunningBack(this);
  }

  public Quarterback toQuarterback() {
    return new Quarterback(this);
  }

  public TightEnd toTightEnd() {
    return new TightEnd(this);
  }

  public WideReceiver toWideReceiver() {
    return new WideReceiver(this);
  }

  public Kicker toKicker() {
    return new Kicker(this);
  }

  public DefenseSpecialTeams toDefenseSpecialTeams() {
    return new DefenseSpecialTeams(this);
  }

  @Override
  public void calculateYahooFantasyPoints(YahooLeagueScoring yahooLeagueScoring) {

  }
}
