package com.finalfantasy.football.players.models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalfantasy.football.stats.YahooLeagueScoring;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.IOException;
import java.util.Map;

@Entity(name = "defenseSpecialTeamss")
public class DefenseSpecialTeams extends AbstractPlayer implements Player {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  short sacks;
  short interception;
  short fumbleRecovery;
  short touchdown;
  short safety;
  short blockKick;
  short pointsAllowed;
  short pointsAllowed0;
  short pointsAllowed1_6;
  short pointsAllowed7_13;
  short pointsAllowed14_20;
  short pointsAllowed21_27;
  short pointsAllowed28_34;
  short pointsAllowedOver35;
  float ydsAllowed;
  short fourthDownStops;
  short extraPointReturned;

  public DefenseSpecialTeams() {
    super(Position.DEF);
  }

  public DefenseSpecialTeams(DefaultPlayer defaultPlayer) {
    super(Position.DEF);
    this.esbid = defaultPlayer.esbid;
    this.gsisPlayerId = defaultPlayer.gsisPlayerId;
    this.name = defaultPlayer.name;
    this.teamAbbr = defaultPlayer.teamAbbr;
    this.apiSeasonPts = defaultPlayer.apiSeasonPts;
    this.apiSeasonProjectedPts = defaultPlayer.apiSeasonProjectedPts;
    this.apiWeekPts = defaultPlayer.apiWeekPts;
    this.apiWeekProjectedPts = defaultPlayer.apiWeekProjectedPts;
  }

  @Override
  public void addStats(JsonNode stats) throws IOException {
    super.addStats(stats);

    ObjectMapper mapper = new ObjectMapper();
    Map<String, String> map = mapper.readValue(stats.toString(), new TypeReference<Map<String, String>>(){});
    map.forEach((key, value) -> {
      switch(Integer.parseInt(key)) {
        case 73:
          this.interception = Short.parseShort(value);
          break;
        case 75:
          this.fumbleRecovery = Short.parseShort(value);
          break;
        case 72:
          this.sacks = Short.parseShort(value);
          break;
        case 80:
          this.safety = Short.parseShort(value);
          break;
        case 76:
        case 77:
        case 78:
          this.touchdown += Short.parseShort(value);
          break;
        case 79:
          this.blockKick = Short.parseShort(value);
          break;
        case 54:
          this.pointsAllowed = Short.parseShort(value);
          break;
        case 55:
          this.pointsAllowed0 = Short.parseShort(value);
          break;
        case 56:
          this.pointsAllowed1_6 = Short.parseShort(value);
          break;
        case 57:
          this.pointsAllowed7_13 = Short.parseShort(value);
          break;
        case 58:
          this.pointsAllowed14_20 = Short.parseShort(value);
          break;
        case 59:
          this.pointsAllowed21_27 = Short.parseShort(value);
          break;
        case 60:
          this.pointsAllowed28_34 = Short.parseShort(value);
          break;
        case 61:
          this.pointsAllowedOver35 = Short.parseShort(value);
          break;
        case 62:
          this.ydsAllowed = Float.parseFloat(value);
          break;
        default:
          log.warn("Did not Find stat for key: {} value: {}", key, value);
          break;
      }
    });
  }

  @Override
  public void calculateYahooFantasyPoints(YahooLeagueScoring yahooLeagueScoring) {

  }
}
