package com.finalfantasy.football.stats;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StatKey extends AbstractStatKey {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  public Integer id;

  Stat toStat() {
    return new Stat(this);
  }
}
