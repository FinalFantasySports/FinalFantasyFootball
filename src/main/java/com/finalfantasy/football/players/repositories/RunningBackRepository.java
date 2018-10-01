package com.finalfantasy.football.players.repositories;

import com.finalfantasy.football.players.models.RunningBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RunningBackRepository extends JpaRepository<RunningBack, Long> {
  public Collection<RunningBack> findAllBySeason(Short season);
  public Collection<RunningBack> findAllBySeasonAndWeek(Short season, Short week);
}
