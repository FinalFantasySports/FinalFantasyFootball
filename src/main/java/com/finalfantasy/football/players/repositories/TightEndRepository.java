package com.finalfantasy.football.players.repositories;

import com.finalfantasy.football.players.models.TightEnd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TightEndRepository extends JpaRepository<TightEnd, Long> {
}
