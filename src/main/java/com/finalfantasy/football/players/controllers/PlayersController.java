package com.finalfantasy.football.players.controllers;

import com.finalfantasy.football.players.models.Quarterback;
import com.finalfantasy.football.players.services.QuarterbackService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "/players")
public class PlayersController {

  QuarterbackService quarterbackService;

  public PlayersController(final QuarterbackService quarterbackService) {
    this.quarterbackService = quarterbackService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public String getPlayers() {
    return "Yo Playa!";
  }

  @GetMapping(value = "/qb")
  public Collection<Quarterback> getQuarterbacks() {
    return quarterbackService.getQuarterbacks();
  }
}
