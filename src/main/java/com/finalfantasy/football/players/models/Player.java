package com.finalfantasy.football.players.models;

import com.finalfantasy.football.AbstractModel;
import com.finalfantasy.football.stats.Stat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Player extends AbstractModel implements Serializable {

  @Id
  public long id;
  public String esbid;
  public String gsisPlayerId;
  public String name;
  Position position;
  public String teamAbbr;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "player_id")
  public Collection<Stat> stat;

  public Player() { }

  public Player(Position position) {
    this.position = position;
  }
}
