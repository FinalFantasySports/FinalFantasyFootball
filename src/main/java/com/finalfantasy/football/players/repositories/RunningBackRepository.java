package com.finalfantasy.football.players.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RunningBackRepository extends JpaRepository<RunningBackRepository, Long> {
}
