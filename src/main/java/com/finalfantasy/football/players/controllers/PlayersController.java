package com.finalfantasy.football.players.controllers;

import com.finalfantasy.football.players.models.Player;
import com.finalfantasy.football.players.services.PlayersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@CrossOrigin
@RestController
@RequestMapping(value = "/players")
public class PlayersController {

  private final PlayersService playersService;

  public PlayersController(final PlayersService playersService) {
    this.playersService = playersService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<Collection<Player>> getPlayers() throws Exception {

    return ResponseEntity.ok(playersService.getAllPlayers());
  }

}
