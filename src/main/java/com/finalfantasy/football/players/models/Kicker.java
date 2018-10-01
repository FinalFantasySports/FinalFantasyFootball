package com.finalfantasy.football.players.models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalfantasy.football.stats.YahooLeagueScoring;

import javax.persistence.*;
import java.io.IOException;
import java.util.Map;

@Entity(name = "kickers")
@Table(indexes = { @Index(name = "IDX_SEASON_WEEK", columnList = "season,week") })
public class Kicker extends AbstractPlayer implements Player {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  short extraPoint;
  short fieldGoalsUnder40;
  short fieldGoals40_49;
  short fieldGoal50orOver;

  public Kicker() {
    super(Position.K);
  }

  public Kicker(DefaultPlayer defaultPlayer) {
    super(Position.K);
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
        case 33:
          this.extraPoint = Short.parseShort(value);
          break;
        case 35:
        case 36:
        case 37:
          this.fieldGoalsUnder40 += Short.parseShort(value);
          break;
        case 38:
          this.fieldGoals40_49 = Short.parseShort(value);
          break;
        case 39:
          this.fieldGoal50orOver = Short.parseShort(value);
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
