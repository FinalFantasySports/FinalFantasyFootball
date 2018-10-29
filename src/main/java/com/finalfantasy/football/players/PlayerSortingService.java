package com.finalfantasy.football.players;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalfantasy.football.players.models.Player;
import com.finalfantasy.football.players.models.Position;
import com.finalfantasy.football.stats.Stat;
import com.finalfantasy.football.stats.StatsService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Service
public class PlayerSortingService {

  private final PlayerRepository playerRepository;
  private final StatsService statsService;

  public PlayerSortingService(final PlayerRepository playerRepository, final StatsService statsService) {
    this.playerRepository = playerRepository;
    this.statsService = statsService;
  }

  public Collection<Stat> getStatsByPlayerIdSeasonAndWeek(long playerId, short season, short week) {
    return statsService.getStatsByPlayerIdSeasonAndWeek(playerId, season, week);
  }

  @Async
  public void sortPlayer(JsonNode node, short season, short week) {
    var player = playerRepository.save(jsonToPlayer(node));
    var statsNode = node.path("stats");
    var stats = new ArrayList<Stat>();
    statsService.getStatKey().forEach(statKey -> {
      ObjectMapper mapper = new ObjectMapper();
      try {
        Map<String, String> map = mapper.readValue(statsNode.toString(), new TypeReference<Map<String, String>>(){});
        map.forEach((key, value) -> {
          if(Integer.parseInt(key) == statKey.id) {
            var stat = new Stat(statKey, Float.parseFloat(value), season, week, player.id);
            stats.add(statsService.saveStat(stat));
          }
        });
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
    player.stat = stats;
    playerRepository.save(player);
  }

  private Player jsonToPlayer(JsonNode playerNode) {

    Player player = new Player(Position.valueOf(playerNode.path("position").toString().replaceAll("\"", "")));
    player.id = Long.parseLong(playerNode.path("id").toString().replaceAll("\"", ""));
    player.esbid = playerNode.path("esbid").toString().replaceAll("\"", "");
    player.gsisPlayerId = playerNode.path("gsisPlayerId").toString().replaceAll("\"", "");
    player.name = playerNode.path("name").toString().replaceAll("\"", "");
    player.teamAbbr = playerNode.path("teamAbbr").toString().replaceAll("\"", "");
    return player;
  }
}
