package com.finalfantasy.football.players;

import com.finalfantasy.football.api.ApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/players")
public class PlayersController {

  private static final Logger log = LoggerFactory.getLogger(PlayersController.class);

  private final PlayersService playersService;
  private final ApiService apiService;

  public PlayersController(final PlayersService playersService, final ApiService ApiService) {
    this.playersService = playersService;
    this.apiService = ApiService;
  }

  @GetMapping
  public ResponseEntity getPlayers(@RequestParam short season, @RequestParam(required = false) Short week,
      @RequestParam(required = false) Position position) {
//    try {
//      return ResponseEntity.ok(playersService.getPlayers(season, week, position));
//    } catch (NoPlayersFoundException e) {
//      log.warn("players not found in database", e);
//      apiService.getPlayers(season);
//      return ResponseEntity.status(204).body("Retrieving players from api. Try again shortly.");
//    }

    apiService.getPlayers(season);
    return ResponseEntity.status(204).body("Retrieving players from api. Try again shortly.");
  }
}
