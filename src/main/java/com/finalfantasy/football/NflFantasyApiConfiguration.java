package com.finalfantasy.football;

import org.springframework.context.annotation.Configuration;

@Configuration
public class NflFantasyApiConfiguration {

  private final static String apiDomain = "http://api.fantasy.nfl.com/v1";
  private static final String playersRoute = "/players";
  private static final String researchInfo = "/researchinfo?season=";
  private static final String and = "&";
  private final static String jsonFormat = "format=json";
  private final static String count = "&count=500";

  public String getPlayerInfoRoute(short season) {
    return apiDomain + playersRoute + researchInfo + season + and + jsonFormat + count;
  }
}
