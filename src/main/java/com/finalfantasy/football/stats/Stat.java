package com.finalfantasy.football.stats;

import com.finalfantasy.football.AbstractModel;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Stat extends AbstractModel implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  public long playerId;
  public int statkey_id;
  public String abbr;
  public String name;
  public String shortName;
  public short season;
  public short week;
  public float value;

  public Stat(){}

  public Stat(short season, short week) {
    this.season = season;
    this.week = week;
  }

  public Stat(StatKey statKey, float value, short season, short week, long playerId) {
    this.statkey_id = statKey.id;
    this.shortName = statKey.shortName;
    this.abbr = statKey.abbr;
    this.name = statKey.name;
    this.value = value;
    this.season = season;
    this.week = week;
    this.playerId = playerId;
  }
}
