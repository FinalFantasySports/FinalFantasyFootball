package com.finalfantasy.football.players.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalfantasy.football.players.models.DefaultPlayer;
import com.finalfantasy.football.players.models.Quarterback;
import com.finalfantasy.football.players.repositories.QuarterbackRepository;
import com.finalfantasy.football.stats.StatKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Service
public class QuarterbackService {

  private static final Logger log = LoggerFactory.getLogger(QuarterbackService.class);

  private final QuarterbackRepository repository;
  private final ArrayList<StatKey> statKeys;

  public QuarterbackService(final QuarterbackRepository repository, final ArrayList<StatKey> statKeys) {
    this.repository = repository;
    this.statKeys = statKeys;
  }

  public Collection<Quarterback> getQuarterbacks() {
    return repository.findAll();
  }

  public void saveQuarterbackAsDefaultPlayer(DefaultPlayer player) {
    repository.save(addStats(player.toQuarterback(), player.stats));
  }

  public void insertQuarterbackAsJsonNode(JsonNode node) {
    Quarterback quarterback = new Quarterback();
    quarterback.esbid = node.path("esbid").toString().replaceAll("\"", "");
    quarterback.gsisPlayerId = node.path("gsisPlayerId").toString().replaceAll("\"", "");
    quarterback.name = node.path("name").toString().replaceAll("\"", "");
    quarterback.teamAbbr = node.path("teamAbbr").toString().replaceAll("\"", "");
    quarterback.apiSeasonPts = node.path("seasonPts").floatValue();
    quarterback.apiSeasonProjectedPts = node.path("seasonProjectedPts").floatValue();
    quarterback.apiWeekPts = node.path("weekPts").floatValue();
    quarterback.apiWeekProjectedPts = node.path("weekProjectedPts").floatValue();

    repository.save(quarterback);
  }

  private Quarterback addStats(Quarterback quarterback, JsonNode stats) {

//    statKeys.stream().filter(statKey -> {
//       stats.hasNonNull(statKey.id)
//    }).collect()


    return quarterback;
  }
}
