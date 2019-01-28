package com.finalfantasy.football.stats;

import com.finalfantasy.football.players.Player;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class StatsByWeek {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  public Long id;

  public int week;
  public int season;
  @OneToMany(mappedBy = "statsByWeek", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  public Collection<Stat> stats;
  public float nflFanPoints;
  public float projectedNflFanPoints;
  @ManyToOne
  @JoinColumn(name = "player_id")
  public Player player;

  public StatsByWeek(final int week, final int season) {
    this.week = week;
    this.season = season;
  }

  public StatsByWeek() {}
}


