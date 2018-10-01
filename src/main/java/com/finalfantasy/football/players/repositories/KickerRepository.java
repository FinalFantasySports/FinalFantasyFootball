package com.finalfantasy.football.players.repositories;

import com.finalfantasy.football.players.models.Kicker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface KickerRepository extends JpaRepository<Kicker, Long> {
  public Collection<Kicker> findAllBySeason(Short season);
  public Collection<Kicker> findAllBySeasonAndWeek(Short season, Short week);
}
