package com.finalfantasy.football.players.models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalfantasy.football.AbstractModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Indexed;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.IOException;
import java.util.Map;

@Indexed
@MappedSuperclass
public abstract class AbstractPlayer extends AbstractModel implements Player {

  private static final Logger log = LoggerFactory.getLogger(AbstractPlayer.class);

  public short season;
  public short week;

  public String esbid;
  public String gsisPlayerId;
  public String name;
  public String teamAbbr;
  public float returnYds;
  public short returnTds;
  public float apiSeasonPts;
  public float apiSeasonProjectedPts;
  public float apiWeekPts;
  public float apiWeekProjectedPts;
  @Transient
  public float fantasyPoints;
  @Transient
  public float valueBaseDraftScore;
  public Position position;

  public AbstractPlayer(Position position) {
    this.position = position;
  }

  @Override
  public void addStats(JsonNode stats) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    Map<String, String> map = mapper.readValue(stats.toString(), new TypeReference<Map<String, String>>(){});
    map.forEach((key, value) -> {
      switch(Integer.parseInt(key)) {
        case 52:
          this.returnYds = Float.parseFloat(value);
          break;
        case 53:
          this.returnTds = Short.parseShort(value);
          break;
        default:
          log.warn("Did not Find stat for key: {} value: {}", key, value);
          break;
      }
    });
  }

  @Override
  public int getFantasyPointsAsInt() {
    return Math.round(this.fantasyPoints);
  }

  @Override
  public float getFantasyPoints() {
    return this.fantasyPoints;
  }

  @Override
  public void setValueBasedDraftScore(float vbd){
    this.valueBaseDraftScore = vbd;
  }

  @Override
  public float getValueBasedDraftScore(){
    return this.valueBaseDraftScore;
  }
}
