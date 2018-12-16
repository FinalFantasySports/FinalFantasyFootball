package com.finalfantasy.football.players;

import com.finalfantasy.football.api.ApiService;
import com.finalfantasy.football.exceptions.NoPlayersFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/players")
public class PlayersController {

  private static final Logger log = LoggerFactory.getLogger(PlayersController.class);

  private final PlayersService playersService;
  private final ApiService apiService;

  @Autowired
  public PlayersController(final PlayersService playersService, final ApiService ApiService) {
    this.playersService = playersService;
    this.apiService = ApiService;
  }

  @GetMapping
  public ResponseEntity getPlayers(@RequestParam(required = false) Short season, @RequestParam(required = false) Short week,
      @RequestParam(required = false) String position) {
    try {
      log.debug("season: {} week: {} position: {}", season, week, position);
      var players = playersService.getPlayers(season, week, position);
      return ResponseEntity.ok(players);
    } catch (NoPlayersFoundException e) {
      log.warn("players not found in database", e);
      var message = "No Players were found in the database. Query for a season and the database will populate with data from that year.";
      if (season > 0) {
        apiService.getPlayers(season);
        message = "Retrieving players from api. Try again shortly.";
      }
      return ResponseEntity.status(204).body(message);
    }
  }
}
