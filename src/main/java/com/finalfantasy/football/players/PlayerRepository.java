package com.finalfantasy.football.players;

import com.finalfantasy.football.players.models.Player;
import com.finalfantasy.football.players.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {
  Collection<Player> findAllByPosition(Position position);
}
