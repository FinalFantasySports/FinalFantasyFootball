package com.finalfantasy.football.players.repositories;

import com.finalfantasy.football.players.models.DefenseSpecialTeams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface DefenseSpecialTeamsRepository extends JpaRepository<DefenseSpecialTeams, Long> {
  public Collection<DefenseSpecialTeams> findAllBySeason(Short season);
  public Collection<DefenseSpecialTeams> findAllBySeasonAndWeek(Short season, Short week);
}
