package com.finalfantasy.football;

import com.finalfantasy.football.players.PlayersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Objects.nonNull;

public class NflFantasyApiConfiguration {

  private static final Logger log = LoggerFactory.getLogger(PlayersService.class);

  private final static String apiDomain = "http://api.fantasy.nfl.com/v1";
  private static final String playersRoute = "/players";
  private static final String researchInfo = "/researchinfo?season=";
  private static final char and = '&';
  private static final char query = '?';
  private final static String season = "season=";
  private final static String week = "week=";
  private final static String jsonFormat = "format=json";
  private final static String count = "&count=500";
  private final static String statsKeyRoute = "/game";
  private final static String stats = "/stats";
  private final static String statTypeWeek = "statType=weekStats";

  public static String getStatsFor(int seasonValue, int weekValue) {
    log.debug("seasonValue: {}", seasonValue);
    var url = apiDomain + playersRoute + stats + query + season + seasonValue + and + statTypeWeek + and;
    if (nonNull(weekValue)) {
      url += week + weekValue + and;
    }
    return url + jsonFormat;
  }

  public static String getPlayerInfoRoute(short season) {
    return apiDomain + playersRoute + researchInfo + season + and + jsonFormat + count;
  }

  public static String getStatsKeyRoute() {
    return apiDomain + statsKeyRoute + stats + query + jsonFormat;
  }
}
