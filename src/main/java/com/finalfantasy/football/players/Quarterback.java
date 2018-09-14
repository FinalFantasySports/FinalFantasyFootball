package com.finalfantasy.football.players;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Quarterback extends AbstractOffensivePlayer implements Player {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  public Quarterback() {
    super(Position.QB);
  }
}
