package com.finalfantasy.football.stats;

import javax.persistence.*;

@Entity
public class Stat extends AbstractStatKey {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  public Long id;
  public int statKeyId;
  public float floatValue;
  public String stringValue;
  public float leaguePointValue;

  @ManyToOne
  @JoinColumn(name = "statsByWeek_id")
  public StatsByWeek statsByWeek;

  Stat(StatKey statKey) {
     this.abbr = statKey.abbr;
     this.name = statKey.name;
     this.shortName = statKey.shortName;
     this.statKeyId = statKey.id;
  }

  public Stat() {};
}
