package com.finalfantasy.football.stats;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface StatRepository extends JpaRepository<Stat, Long> {
  Collection<Stat> findAllBySeasonAndWeek(short season, short week);
  Collection<Stat> findAllByPlayerIdAndSeasonAndWeek(long playerId, short season, short week);
}
