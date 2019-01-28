package com.finalfantasy.football.players;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.finalfantasy.football.AbstractModel;
import com.finalfantasy.football.stats.StatsByWeek;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

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

  @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JsonManagedReference
  public Collection<StatsByWeek> statsByWeeks;

  public Player() {
    super();
  }

  void setProperty(String key, Object value) {
    if (value != null && key != null) {
      var stringValue = value.toString();
      switch (key) {
        case depthChartOrderKey:
          this.depthChartOrder = Short.parseShort(stringValue);
          break;
        case numberOfAddsKey:
          this.numberOfAdds = Short.parseShort(stringValue);
          break;
        case numberOfDropsKey:
          this.numberOfDrops = Short.parseShort(stringValue);
          break;
        case esbidKey:
          this.esbid = stringValue;
          break;
        case gsisPlayerIdKey:
          this.gsisPlayerId = stringValue;
          break;
        case firstNameKey:
          this.firstName = stringValue;
          break;
        case lastNameKey:
          this.lastName = stringValue;
          break;
        case teamAbbrKey:
          this.teamAbbr = stringValue;
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
