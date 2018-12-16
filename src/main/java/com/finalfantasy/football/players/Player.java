package com.finalfantasy.football.players;

import com.finalfantasy.football.AbstractModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "players")
public class Player extends AbstractModel {

  private static final Logger log = LoggerFactory.getLogger(Player.class);

  private static final String esbidKey = "esbid";
  private static final String gsisPlayerIdKey = "gsisPlayerId";
  private static final String firstNameKey = "firstName";
  private static final String lastNameKey = "lastName";
  private static final String teamAbbrKey = "teamAbbr";
  private static final String depthChartOrderKey = "depthChartOrder";
  private static final String numberOfAddsKey = "numAdds";
  private static final String numberOfDropsKey = "numDrops";

  @Id
  public Long id;
  public Short year;
  public String esbid;
  public String gsisPlayerId;
  public String firstName;
  public String lastName;
  public String teamAbbr;
  public String position;
  public Short depthChartOrder;
  public Short numberOfAdds;
  public Short numberOfDrops;

  public Player() {
    super();
  }

  void setProperty(String key, String value) {
    switch (key) {
      case depthChartOrderKey:
        this.depthChartOrder = Short.parseShort(value);
        break;
      case numberOfAddsKey:
        this.numberOfAdds = Short.parseShort(value);
        break;
      case numberOfDropsKey:
        this.numberOfDrops = Short.parseShort(value);
        break;
      case esbidKey:
        this.esbid = value;
        break;
      case gsisPlayerIdKey:
        this.gsisPlayerId = value;
        break;
      case firstNameKey:
        this.firstName = value;
        break;
      case lastNameKey:
        this.lastName = value;
        break;
      case teamAbbrKey:
        this.teamAbbr = value;
        break;
      default:
        break;
    }
  }

  @Override
  public String toString() {
    return "id: " + id + " year: " + year + " esbid: " + esbid + " numberOfAdds: " + numberOfAdds;
  }
}
