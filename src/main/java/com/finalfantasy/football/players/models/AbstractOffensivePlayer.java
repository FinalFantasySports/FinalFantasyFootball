package com.finalfantasy.football.players.models;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractOffensivePlayer extends AbstractPlayer implements Player {

  public Float rushingYds;
  public Short rushingTds;
  public Short receptions;
  public Float receivingYds;
  public Short receivingTds;
  public Short fumblesLost;
  public Short twoPointCon;
  public Float returnYds;
  public Short returnTds;
  public Short fumblesRecTds;

  public AbstractOffensivePlayer(Position position) {
    super(position);
  }

  @Override
  public Float calculateFantasyPoints() {
    return null;
  }
}
