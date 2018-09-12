package com.finalfantasy.football.players;

import com.finalfantasy.football.WeekData;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/players")
public class PlayersController {

  private final PlayersService playersService;

  public PlayersController(final PlayersService playersService) {
    this.playersService = playersService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public String populateDatabaseBySeasonAndWeek() {
    return "We did it! " + playersService.populateDatabaseBySeasonAndWeek();
  }

//  @RequestMapping(method = RequestMethod.GET)
//  public String getPlayers() {
//    return "Yo Playa!";
//  }

}
