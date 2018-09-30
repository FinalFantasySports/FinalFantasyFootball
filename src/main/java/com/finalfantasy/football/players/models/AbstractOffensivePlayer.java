package com.finalfantasy.football.players.models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalfantasy.football.stats.YahooLeagueScoring;

import javax.persistence.MappedSuperclass;
import java.io.IOException;
import java.util.Map;

@MappedSuperclass
public abstract class AbstractOffensivePlayer extends AbstractPlayer implements Player {

  public float rushingYds;
  public short rushingTds;
  public short receptions;
  public float receivingYds;
  public short receivingTds;
  public short fumblesLost;
  public short twoPointCon;

  public short fumblesRecTds;

  public AbstractOffensivePlayer(Position position) {
    super(position);
  }


  @Override
  public void addStats(JsonNode stats) throws IOException {
    super.addStats(stats);
    ObjectMapper mapper = new ObjectMapper();
    Map<String, String> map = mapper.readValue(stats.toString(), new TypeReference<Map<String, String>>(){});
    map.forEach((key, value) -> {
      switch(Integer.parseInt(key)) {
        case 14:
          this.rushingYds = Float.parseFloat(value);
          break;
        case 15:
          this.rushingTds = Short.parseShort(value);
          break;
        case 20:
          this.receptions = Short.parseShort(value);
          break;
        case 21:
          this.receivingYds = Float.parseFloat(value);
          break;
        case 22:
          this.receivingTds = Short.parseShort(value);
          break;
        case 30:
          this.fumblesLost = Short.parseShort(value);
          break;
        case 32:
          this.twoPointCon = Short.parseShort(value);
          break;
        case 29:
          this.fumblesRecTds = Short.parseShort(value);
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
