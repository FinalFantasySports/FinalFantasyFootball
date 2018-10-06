package com.finalfantasy.football.players.models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.io.IOException;
import java.util.Map;

@Entity(name = "quarterbacks")
@Table(indexes = { @Index(name = "IDX_SEASON_WEEK", columnList = "season,week") })
public class Quarterback extends AbstractOffensivePlayer implements Player {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  public float passingYds;
  public short passingTds;
  public short passingInt;
  public short qbSacks;

  public Quarterback() {
    super(Position.QB);
  }

  public Quarterback(DefaultPlayer defaultPlayer) {
    super(Position.QB);
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
        case 5:
          this.passingYds = Float.parseFloat(value);
          break;
        case 6:
          this.passingTds = Short.parseShort(value);
          break;
        case 7:
          this.passingInt = Short.parseShort(value);
          break;
        case 8:
          this.qbSacks = Short.parseShort(value);
          break;
        default:
//            log.warn("Did not Find stat for key: {} value: {}", key, value);
            break;
      }
    });
  }

}
