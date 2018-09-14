package com.finalfantasy.football.players.repositories;

import com.finalfantasy.football.players.models.Kicker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KickerRepository extends JpaRepository<Kicker, Integer> {
}
