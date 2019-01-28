package com.finalfantasy.football.players;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PlayersRepository extends JpaRepository<Player, Long> {
  Collection<Player> findAllByPositionAndYear(String position, short year);
  Collection<Player> findAllByYear(short year);
  Collection<Player> findTop200ByYear(short year);
}
