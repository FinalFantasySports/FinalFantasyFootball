package com.finalfantasy.football.players;

import com.finalfantasy.football.api.ApiService;
import com.finalfantasy.football.exceptions.NoPlayersFoundException;
import com.finalfantasy.football.players.models.*;
import com.finalfantasy.football.stats.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;

@Service
public class PlayersService {

  private static final Logger log = LoggerFactory.getLogger(PlayersService.class);

  private final PlayerRepository playerRepository;
  private final ApiService apiService;
  private final PlayerSortingService sortingService;

  public PlayersService(final PlayerRepository playerRepository, final ApiService apiService, final PlayerSortingService sortingService) {
    this.sortingService = sortingService;
    this.playerRepository = playerRepository;
    this.apiService = apiService;
  }

  Collection<Player> getPlayers(short season, short week, Position position) throws NoPlayersFoundException, IOException {

//    var stat = new Stat();
//    stat.season = season;
//    stat.week = week;
    Collection<Player> players;

    if (position != null) {
      players = playerRepository.findAllByPosition(position);
    } else {
      players = playerRepository.findAll();
    }

    if (players.size() < 1) {
      if(getPlayersFromApi(season, week)) {
        if (position != null) {
          players = playerRepository.findAllByPosition(position);
        } else {
          players = playerRepository.findAll();
        }
        if (players.size() < 1) {
          throw new NoPlayersFoundException();
        }
      }
    }
    players.parallelStream().forEach(player -> player.stat = sortingService.getStatsByPlayerIdSeasonAndWeek(player.id, season, week));
    return players;
  }

  private boolean getPlayersFromApi(short season, short week) throws IOException {
    var playersNode = apiService.getPlayerStatsByWeekRoute(season, week);
    playersNode.iterator().forEachRemaining(player -> sortingService.sortPlayer(player, season, week));
    return true;
  }
}
