package com.finalfantasy.football.players.models;

import com.finalfantasy.football.AbstractModel;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@MappedSuperclass
public abstract class AbstractPlayer extends AbstractModel implements Player {

  public Map<String, Float> stats = new HashMap<>();

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
