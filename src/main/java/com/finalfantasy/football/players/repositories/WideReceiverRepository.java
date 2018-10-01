package com.finalfantasy.football.players.repositories;

import com.finalfantasy.football.players.models.WideReceiver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface WideReceiverRepository extends JpaRepository<WideReceiver, Long> {
  public Collection<WideReceiver> findAllBySeason(Short season);
  public Collection<WideReceiver> findAllBySeasonAndWeek(Short season, Short week);
}
