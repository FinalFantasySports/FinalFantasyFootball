package com.finalfantasy.football.stats;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Map;

@Entity
public class StatsByWeek {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  public Long id;

  public short week;
  public short season;
  public Map<Integer, String> stats;
  public float nflFanPoints;
  public float projectedNflFanPoints;
}
