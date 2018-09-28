package com.finalfantasy.football.players.models;

import com.finalfantasy.football.AbstractModel;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractPlayer extends AbstractModel implements Player {

  public String esbid;
  public String gsisPlayerId;
  public String name;
  public String teamAbbr;
  public Float apiSeasonPts;
  public Float apiSeasonProjectedPts;
  public Float apiWeekPts;
  public Float apiWeekProjectedPts;
  public final Position position;

  public AbstractPlayer(Position position) {
    this.position = position;
  }

  @Override
  public Float calculateFantasyPoints() {
    return null;
  }

}
