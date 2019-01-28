package com.finalfantasy.football.stats;

import com.finalfantasy.football.AbstractModel;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractStatKey extends AbstractModel {
  public String abbr;
  public String name;
  public String shortName;
}
