package com.finalfantasy.football.players;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PlayersService {

  private static final Logger log = LoggerFactory.getLogger(PlayersService.class);

  private final PlayersRepository repository;

  public PlayersService(final PlayersRepository repository) {
    this.repository = repository;
  }

  public void savePlayerFromMap(Map playerMap, short year) {

    Player player = new Player();
    player.year = year;
    player.position = playerMap.getOrDefault("position", "Not Present").toString();
    playerMap.keySet().iterator().forEachRemaining(key ->
      player.setProperty(key.toString(),playerMap.get(key) )
    );
    log.info("Saving Player: {}", player.toString());
    repository.save(player);
  }

//  Collection<Player> getPlayers(short season, short week, Position position) throws NoPlayersFoundException {
//    Collection<Player> players = new ArrayList<>();
////    if (position != null) {
////      players = playerRepository.findAllByPositionAndYear(position, season);
////    } else {
////      players = playerRepository.findAllByYear(season);
////    }
//    if (players.size() <= 0) {
//      throw new NoPlayersFoundException();
//    }
//    return players;
//  }

}
