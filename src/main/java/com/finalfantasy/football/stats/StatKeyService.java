package com.finalfantasy.football.stats;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StatKeyService {

  private static final Logger log = LoggerFactory.getLogger(StatKeyService.class);

  private final StatKeyRepository repository;

  public StatKeyService(final StatKeyRepository repository) {
    this.repository = repository;
  }

  public Collection<StatKey> getStatKeys() {
    return repository.findAll();
  }

  @Async
  public void saveStatKeyFromJsonNode(JsonNode statKeyNode) {
    var statKey = new StatKey();
    log.debug("statKeyNode.path(\"id\").toString()\": {}", statKeyNode.path("id").toString());
    statKey.id = statKeyNode.path("id").asInt();
    statKey.abbr = statKeyNode.path("abbr").textValue();
    log.debug("statKeyNode.path(\"name\").toString()\": {}", statKeyNode.path("name").toString());
    statKey.name = statKeyNode.path("name").textValue();
    statKey.shortName = statKeyNode.path("shortName").textValue();
    repository.save(statKey);
  }
}
