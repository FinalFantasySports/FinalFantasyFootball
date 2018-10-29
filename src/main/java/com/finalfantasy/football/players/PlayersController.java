package com.finalfantasy.football.players;

import com.finalfantasy.football.exceptions.NoPlayersFoundException;
import com.finalfantasy.football.players.models.Position;
import com.finalfantasy.football.stats.StatsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping(value = "/players")
public class PlayersController {

  private static final Logger log = LoggerFactory.getLogger(PlayersController.class);

  private final PlayersService playersService;
  private final StatsService statsService;

  public PlayersController(final PlayersService playersService, final StatsService statsService) {
    this.playersService = playersService;
    this.statsService = statsService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity getPlayers(@RequestParam short season, @RequestParam short week, @RequestParam(required = false) Position position) {

    try {
      return ResponseEntity.ok(playersService.getPlayers(season, week, position));
    } catch (NoPlayersFoundException e) {
      log.warn("players not found in database", e);
      try {
        return ResponseEntity.ok(playersService.getPlayers( season, week , position));
      } catch (IOException e1) {
        e1.printStackTrace();
        return ResponseEntity.status(500).build();
      } catch (NoPlayersFoundException e1) {
        e1.printStackTrace();
        return ResponseEntity.noContent().build();
      }
    } catch (IOException e) {
      e.printStackTrace();
      return ResponseEntity.status(500).build();
    }
  }

}
