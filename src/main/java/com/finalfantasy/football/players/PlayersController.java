package com.finalfantasy.football.players;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/players")
public class PlayersController {

  @RequestMapping(method = RequestMethod.GET)
  public String getPlayers() {
    return "Yo Playa!";
  }
}
