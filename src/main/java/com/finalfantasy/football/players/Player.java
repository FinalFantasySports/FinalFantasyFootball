package com.finalfantasy.football.players;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "players")
public class Player {

  private static final Logger log = LoggerFactory.getLogger(Player.class);

  private static final String idKey = "id";
  private static final String esbidKey = "esbid";
  private static final String gsisPlayerIdKey = "gsisPlayerId";
  private static final String firstNameKey = "firstName";
  private static final String lastNameKey = "lastName";
  private static final String teamAbbrKey = "teamAbbr";
  private static final String depthChartOrderKey = "depthChartOrder";
  private static final String numberOfAddsKey = "numberOfAdds";
  private static final String numberOfDropsKey = "numberOfDrops";

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
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

  void setProperty(String key, Object value) {
    if (value instanceof Long && idKey.equals(key)) {
      this.id = (Long) value;
    } else if (value instanceof Short) {
      switch (key) {
        case depthChartOrderKey:
          this.depthChartOrder = (Short) value;
          break;
        case numberOfAddsKey:
          this.numberOfAdds = (Short) value;
          break;
        case numberOfDropsKey:
          this.numberOfDrops = (Short) value;
          break;
        default:
          break;
      }
    } else if (value instanceof String) {
      switch (key) {
        case esbidKey:
          this.esbid = value.toString();
          break;
        case gsisPlayerIdKey:
          this.gsisPlayerId = value.toString();
          break;
        case firstNameKey:
          this.firstName = value.toString();
          break;
        case lastNameKey:
          this.lastName = value.toString();
          break;
        case teamAbbrKey:
          this.teamAbbr = value.toString();
          break;
        default:
          break;
      }
    }
  }

  @Override
  public String toString() {
    return "id: " + id + " year: " + year + " esbid: " + esbid + " numberOfAdds: " + numberOfAdds;
  }
}
