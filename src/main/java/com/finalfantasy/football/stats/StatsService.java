package com.finalfantasy.football.stats;

import com.finalfantasy.football.players.models.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class StatsService {

  private static final Logger log = LoggerFactory.getLogger(StatsService.class);
  private final StatKeyRepository statKeyRepository;
  private final StatRepository statRepository;

  public StatsService(final StatKeyRepository statKeyRepository, final StatRepository statRepository) {
    this.statKeyRepository = statKeyRepository;
    this.statRepository = statRepository;
  }

  public Collection<StatKey> getStatKey() {
    return statKeyRepository.findAll();
  }

  public Collection<Stat> getStatsByPlayerSeasonAndWeek(short season, short week) {
    return statRepository.findAllBySeasonAndWeek(season, week );
  }

  public Collection<Stat> getStatsByPlayerIdSeasonAndWeek(long playerId, short season, short week) {
    return statRepository.findAllByPlayerIdAndSeasonAndWeek(playerId, season, week);
  }

  public Stat saveStat(Stat stat) {
    return statRepository.save(stat);
  }

  public Collection<Stat> saveAllStats(Collection<Stat> stats) {
    return statRepository.saveAll(stats);
  }

}
