package com.finalfantasy.football.players.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Player")
public class DefaultPlayer extends AbstractPlayer implements Player {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

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
}
