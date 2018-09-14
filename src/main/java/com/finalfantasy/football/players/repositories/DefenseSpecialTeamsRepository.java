package com.finalfantasy.football.players.repositories;

import com.finalfantasy.football.players.models.DefenseSpecialTeams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefenseSpecialTeamsRepository extends JpaRepository<DefenseSpecialTeams, Long> {
}
