package com.finalfantasy.football.players;

import com.finalfantasy.football.exceptions.NoPlayersFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
public class PlayersService {

  private static final Logger log = LoggerFactory.getLogger(PlayersService.class);

  private final PlayersRepository repository;

  @Autowired
  public PlayersService(final PlayersRepository repository) {
    this.repository = repository;
  }

  public void savePlayerFromMap(Map playerMap, short year) {

    Player player = new Player();
    player.year = year;
    player.id = Long.parseLong(playerMap.get("id").toString());
    player.position = playerMap.getOrDefault("position", "Not Present").toString();
    playerMap.keySet().iterator().forEachRemaining(key ->
      player.setProperty(key.toString(),playerMap.get(key) )
    );
    log.info("Saving Player: {}", player.toString());
    repository.save(player);
  }

  public Collection<Player> getPlayers(short season, Short week, String position) throws NoPlayersFoundException {
    Collection<Player> players;
    if (season > 0) {
      if (position != null) {
        players = repository.findAllByPositionAndYear(position, season);
      } else {
        players = repository.findAllByYear(season);
      }
    } else {
      players = repository.findAll();
    }
    if (players.size() < 1) {
      throw new NoPlayersFoundException();
    }
    return players;
  }

}
