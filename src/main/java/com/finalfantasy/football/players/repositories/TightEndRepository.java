package com.finalfantasy.football.players.repositories;

import com.finalfantasy.football.players.models.TightEnd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface TightEndRepository extends JpaRepository<TightEnd, Long> {
  public Collection<TightEnd> findAllBySeason(Short season);
  public Collection<TightEnd> findAllBySeasonAndWeek(Short season, Short week);
}
