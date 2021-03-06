package com.finalfantasy.football.stats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatKeyRepository extends JpaRepository<StatKey, Integer> {
}
