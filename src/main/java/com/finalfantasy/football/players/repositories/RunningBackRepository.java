package com.finalfantasy.football.players.repositories;

import com.finalfantasy.football.players.models.RunningBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RunningBackRepository extends JpaRepository<RunningBack, Long> {
}
