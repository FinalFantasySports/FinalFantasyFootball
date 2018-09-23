package com.finalfantasy.football.players.repositories;

import com.finalfantasy.football.players.models.DefaultPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<DefaultPlayer, Long> {
}
