package com.finalfantasy.football.players.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Player")
public class MiscPlayer extends AbstractPlayer implements Player {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  public String miscPosition;

  public MiscPlayer(String miscPosition) {
    super(Position.MISC);
    this.miscPosition = miscPosition;
  }
}
