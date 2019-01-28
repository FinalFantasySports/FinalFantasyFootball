package com.finalfantasy.football.stats;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.finalfantasy.football.players.Player;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class StatsByWeek {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  public Long id;

  public int week;
  public int season;

  @OneToMany(mappedBy = "statsByWeek", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JsonManagedReference
  public Collection<Stat> stats;

  public float nflFanPoints;
  public float projectedNflFanPoints;

  @ManyToOne(optional = false)
  @JoinColumn(name = "player_id")
  @JsonBackReference
  public Player player;

  public StatsByWeek(final int week, final int season) {
    this.week = week;
    this.season = season;
  }

  public StatsByWeek() {}
}


