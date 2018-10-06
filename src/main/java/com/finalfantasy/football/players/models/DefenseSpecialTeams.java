package com.finalfantasy.football.players.models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.io.IOException;
import java.util.Map;

@Entity(name = "defenseSpecialTeamss")
@Table(indexes = { @Index(name = "IDX_SEASON_WEEK", columnList = "season,week") })
public class DefenseSpecialTeams extends AbstractPlayer implements Player {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  public short defensiveSack;
  public short defensiveInterception;
  public short fumbleRecovery;
  public short touchdown;
  public short safety;
  public short blockKick;
  public short pointsAllowed;
  public short pointsAllowed0;
  public short pointsAllowed1_6;
  public short pointsAllowed7_13;
  public short pointsAllowed14_20;
  public short pointsAllowed21_27;
  public short pointsAllowed28_34;
  public short pointsAllowedOver35;
  public float ydsAllowed;
  public short fourthDownStops;
  public short extraPointReturned;

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
    this.season = defaultPlayer.season;
    this.week = defaultPlayer.week;
  }

  @Override
  public void addStats(JsonNode stats) throws IOException {
    super.addStats(stats);

    ObjectMapper mapper = new ObjectMapper();
    Map<String, String> map = mapper.readValue(stats.toString(), new TypeReference<Map<String, String>>(){});
    map.forEach((key, value) -> {
      switch(Integer.parseInt(key)) {
        case 73:
          this.defensiveInterception = Short.parseShort(value);
          break;
        case 75:
          this.fumbleRecovery = Short.parseShort(value);
          break;
        case 72:
          this.defensiveSack = Short.parseShort(value);
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
//          log.warn("Did not Find stat for key: {} value: {}", key, value);
          break;
      }
    });
  }

}
