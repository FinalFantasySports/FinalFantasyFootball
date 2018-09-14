package com.finalfantasy.football.players.repositories;

import com.finalfantasy.football.players.models.Quarterback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuarterbackRepository extends JpaRepository<Quarterback, Long> {
}