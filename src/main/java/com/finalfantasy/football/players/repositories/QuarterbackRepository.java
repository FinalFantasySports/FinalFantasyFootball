package com.finalfantasy.football.players.repositories;

import com.finalfantasy.football.players.models.Quarterback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface QuarterbackRepository extends JpaRepository<Quarterback, Long> {
  public Collection<Quarterback> findAllBySeason(Short season);
  public Collection<Quarterback> findAllBySeasonAndWeek(Short season, Short week);
}
