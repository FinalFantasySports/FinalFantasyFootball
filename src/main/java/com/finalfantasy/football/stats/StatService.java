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

    if (playerMap.containsKey("id")) {

      var playerOpt = playersService.getPlayerById(Long.parseLong(playerMap.get("id").toString()));

      playerOpt.ifPresent(player -> {

        StatsByWeek statsByWeek = new StatsByWeek(week, season);
        statsByWeek.player = player;
        statsByWeek.nflFanPoints = Float.parseFloat(playerMap.getOrDefault("weekPts", 0).toString());
        statsByWeek.projectedNflFanPoints = Float.parseFloat(
            playerMap.getOrDefault("weekProjectedPts", 0).toString());

        var statsMap = (Map<String, String>) playerMap.getOrDefault("stats", new HashMap<>());
        var statKeys = statKeyService.getStatKeys();

        var stats = statKeys.stream().filter(statKey ->
            statsMap.containsKey(Integer.toString(statKey.id))).map(StatKey::toStat).collect(Collectors.toList());
        var savedStatsByWeek = statsByWeekRepository.save(statsByWeek);
        savedStatsByWeek.stats = new ArrayList<>();

        stats.forEach(stat -> {
          var statId = Long.toString(stat.statKeyId);
          var statValue = statsMap.get(statId);
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
      });
    } else {
      log.debug("No id here: {}", playerMap.toString());
    }
  }
}
