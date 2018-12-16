package com.finalfantasy.football.players;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayersRepository extends JpaRepository<Player, Long> {
}
