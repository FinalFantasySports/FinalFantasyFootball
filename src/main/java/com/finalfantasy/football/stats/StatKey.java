package com.finalfantasy.football.stats;

import com.finalfantasy.football.AbstractModel;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StatKey extends AbstractModel implements Data {

  @Id
  public int id;
  public String abbr;
  public String name;
  public String shortName;

}
