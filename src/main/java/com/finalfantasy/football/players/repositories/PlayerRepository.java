package com.finalfantasy.football.players.repositories;

import com.finalfantasy.football.players.models.DefaultPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PlayerRepository extends JpaRepository<DefaultPlayer, Long> {
  public Collection<DefaultPlayer> findAllBySeason(Short season);
  public Collection<DefaultPlayer> findAllBySeasonAndWeek(Short season, Short week);
}
