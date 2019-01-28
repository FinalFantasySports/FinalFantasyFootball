package com.finalfantasy.football.stats;

import com.finalfantasy.football.players.PlayersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatService {

  private static final Logger log = LoggerFactory.getLogger(StatService.class);

  private final StatsByWeekRepository statsByWeekRepository;
  private final StatRepository statRepository;
  private final PlayersService playersService;
  private final StatKeyService statKeyService;

  public StatService(final StatsByWeekRepository statsByWeekRepository, final PlayersService playersService,
      final StatKeyService statKeyService, final StatRepository statRepository) {
    this.statsByWeekRepository = statsByWeekRepository;
    this.playersService = playersService;
    this.statKeyService = statKeyService;
    this.statRepository = statRepository;
  }

  @Async
  public void saveStatsFromMap(Map playerMap, int season, int week) {

    StatsByWeek statsByWeek = new StatsByWeek(week, season);
    statsByWeek.nflFanPoints = Float.parseFloat(playerMap.getOrDefault("weekPts", 0).toString());
    statsByWeek.projectedNflFanPoints = Float.parseFloat(
        playerMap.getOrDefault("weekProjectedPts",0 ).toString());
    var savedStatsByWeek = statsByWeekRepository.save(statsByWeek);
    var statsMap = (Map<String, String>) playerMap.getOrDefault("stats", new HashMap<>());
    var statKeys = statKeyService.getStatKeys();

    var stats = statKeys.stream().filter(statKey ->
        statsMap.containsKey(Integer.toString(statKey.id))).map(StatKey::toStat).collect(Collectors.toList());
    savedStatsByWeek.stats = new ArrayList<>();

    stats.forEach(stat -> {
      log.debug("stat.statKeyId: {}", stat.statKeyId);
      var statId = Long.toString(stat.statKeyId);
      log.debug("statId: {}", statId);
      var statValue = statsMap.get(statId);
      log.debug("statValue: {}", statValue);
      try {
        stat.floatValue = Float.parseFloat(statValue);
      } catch (NumberFormatException e) {
        log.warn("Unable to parse statValue as float", e);
        stat.stringValue = statValue;
      }
      stat.statsByWeek = savedStatsByWeek;
      stat = statRepository.save(stat);
      savedStatsByWeek.stats.add(stat);
    });
    var updatedStatsByWeek = statsByWeekRepository.save(savedStatsByWeek);

    if (playerMap.containsKey("id")) {
      try {
        var playerOpt = playersService.getPlayerById(Long.parseLong(playerMap.get("id").toString()));
        playerOpt.ifPresent(player -> {
          updatedStatsByWeek.player = player;
          statsByWeekRepository.save(updatedStatsByWeek);
          if (player.statsByWeeks != null) {
            player.statsByWeeks.add(updatedStatsByWeek);
          } else {
            player.statsByWeeks = new ArrayList<>() {{add(updatedStatsByWeek);}};
          }
          playersService.savePlayer(player);
        });
      } catch (NumberFormatException e) {
        log.error("Failed to retrieve player id", e);
      }
    }
    statsByWeekRepository.save(updatedStatsByWeek);
  }
}
