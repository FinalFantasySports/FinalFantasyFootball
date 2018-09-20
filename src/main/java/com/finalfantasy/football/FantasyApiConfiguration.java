package com.finalfantasy.football;

import org.springframework.context.annotation.Configuration;

@Configuration
public class FantasyApiConfiguration {

  public final static String apiDomain = "http://api.fantasy.nfl.com";
  public final static String statKeyRoute = "/v1/game/stats";
  public final static String jsonFormat = "?format=json";

  public String getStatKeyRoute() {
    StringBuilder stringBuilder = new StringBuilder(apiDomain);
    stringBuilder.append(statKeyRoute);
    stringBuilder.append(jsonFormat);
    return stringBuilder.toString();
  }
}
