package com.finalfantasy.football.league;

import com.finalfantasy.football.players.models.*;

public class YahooLeagueScoring implements LeagueScoring {

  //private static final Logger log = LoggerFactory.getLogger(YahooLeagueScoring.class);

  private final static short passingYds = 25;
  private final static short passingTds = 4;
  private final static short passingInt = -1;
  private final static short qbSacks = -1;
  private final static short rushingYds = 10;
  private final static short rushingTds = 6;
  private final static short receptions = 1;
  private final static short receivingYds = 10;
  private final static short receivingTds = 6;
  private final static short returnYds = 10;
  private final static short returnTds = 6;
  private final static short twoPointCon = 2;
  private final static short fumblesLost = -2;
  private final static short offensiveFumbleReturnTds = 6;
  private final static short fieldGoalsUnder40 = 3;
  private final static short fieldGoals40_49 = 4;
  private final static short getFieldGoalsOver50 = 5;
  private final static short extraPoint = 1;

  private final static short defensiveSack = 1;
  private final static short defensiveInterception = 2;
  private final static short fumbleRecovery = 2;
  private final static short touchdown = 6;
  private final static short safety = 2;
  private final static short blockKick = 2;
  private final static short pointsAllowed0 = 10;
  private final static short pointsAllowed1_6 = 7;
  private final static short pointsAllowed7_13 = 4;
  private final static short pointsAllowed14_20 = 1;
  private final static short pointsAllowed21_27 = 0;
  private final static short pointsAllowed28_34 = -1;
  private final static short pointsAllowedOver35 = -4;
  private final static short fourthDownStops = 1;
  private final static short extraPointReturned = 2;

  @Override
  public float calculateFantasyPts(Player player) {

    float fanPoints = 0;

    if(player instanceof AbstractPlayer) {
      fanPoints += ((AbstractPlayer) player).returnYds / returnYds;
      fanPoints += ((AbstractPlayer) player).returnTds * returnTds;
    }



    if(player instanceof AbstractOffensivePlayer) {
      fanPoints += ((AbstractOffensivePlayer) player).rushingYds / rushingYds;
      fanPoints += ((AbstractOffensivePlayer) player).rushingTds * rushingTds;
      fanPoints += ((AbstractOffensivePlayer) player).receptions * receptions;
      fanPoints += ((AbstractOffensivePlayer) player).receivingYds / receivingYds;
      fanPoints += ((AbstractOffensivePlayer) player).receivingTds * receivingTds;
      fanPoints += ((AbstractOffensivePlayer) player).fumblesLost * fumblesLost;
      fanPoints += ((AbstractOffensivePlayer) player).twoPointCon * twoPointCon;
      fanPoints += ((AbstractOffensivePlayer) player).offensiveFumbleReturnTds * offensiveFumbleReturnTds;
    }

    if(player instanceof Quarterback) {
      fanPoints += ((Quarterback) player).passingYds / passingYds;
      fanPoints += ((Quarterback) player).passingTds * passingTds;
      fanPoints += ((Quarterback) player).passingInt * passingInt;
      fanPoints += ((Quarterback) player).qbSacks * qbSacks;
    }

    if(player instanceof Kicker) {
      fanPoints += ((Kicker) player).extraPoint * extraPoint;
      fanPoints += ((Kicker) player).fieldGoalsUnder40 * fieldGoalsUnder40;
      fanPoints += ((Kicker) player).fieldGoals40_49 * fieldGoals40_49;
      fanPoints += ((Kicker) player).getFieldGoalsOver50 * getFieldGoalsOver50;
    }

    if(player instanceof DefenseSpecialTeams) {
      fanPoints += ((DefenseSpecialTeams) player).defensiveSack * defensiveSack;
      fanPoints += ((DefenseSpecialTeams) player).defensiveInterception * defensiveInterception;
      fanPoints += ((DefenseSpecialTeams) player).fumbleRecovery * fumbleRecovery;
      fanPoints += ((DefenseSpecialTeams) player).touchdown * touchdown;
      fanPoints += ((DefenseSpecialTeams) player).blockKick * blockKick;
      fanPoints += ((DefenseSpecialTeams) player).safety * safety;
      fanPoints += ((DefenseSpecialTeams) player).pointsAllowed0 * pointsAllowed0;
      fanPoints += ((DefenseSpecialTeams) player).pointsAllowed1_6 * pointsAllowed1_6;
      fanPoints += ((DefenseSpecialTeams) player).pointsAllowed7_13 * pointsAllowed7_13;
      fanPoints += ((DefenseSpecialTeams) player).pointsAllowed14_20 * pointsAllowed14_20;
      fanPoints += ((DefenseSpecialTeams) player).pointsAllowed21_27 * pointsAllowed21_27;

      fanPoints += ((DefenseSpecialTeams) player).pointsAllowed28_34 * pointsAllowed28_34;
      fanPoints += ((DefenseSpecialTeams) player).pointsAllowedOver35 * pointsAllowedOver35;
      fanPoints += ((DefenseSpecialTeams) player).fourthDownStops * fourthDownStops;
      fanPoints += ((DefenseSpecialTeams) player).extraPointReturned * extraPointReturned;
    }

    return fanPoints;
  }
}
