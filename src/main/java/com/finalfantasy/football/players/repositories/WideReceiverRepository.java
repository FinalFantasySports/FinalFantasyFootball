package com.finalfantasy.football.players.repositories;

import com.finalfantasy.football.players.models.WideReceiver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WideReceiverRepository extends JpaRepository<WideReceiver, Integer> {
}
