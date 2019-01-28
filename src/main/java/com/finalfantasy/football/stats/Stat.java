package com.finalfantasy.football.stats;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "stats")
public class Stat extends AbstractStatKey {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  public Long id;

  public int statKeyId;
  public float floatValue;
  public String stringValue;
  public float leaguePointValue;

  @ManyToOne(optional = false)
  @JoinColumn(name = "statsByWeek_id")
  @JsonBackReference
  public StatsByWeek statsByWeek;

  Stat(StatKey statKey) {
     this.abbr = statKey.abbr;
     this.name = statKey.name;
     this.shortName = statKey.shortName;
     this.statKeyId = statKey.id;
  }

  public Stat() {}
}
