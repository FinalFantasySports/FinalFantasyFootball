package com.finalfantasy.football.players;

public abstract class AbstractOffensivePlayer extends AbstractPlayer implements Player {

  public AbstractOffensivePlayer(Position position) {
    super(position);
  }

  @Override
  public Float calculateFantasyPoints() {
    return null;
  }
}
