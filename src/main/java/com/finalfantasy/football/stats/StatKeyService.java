package com.finalfantasy.football.stats;

import com.finalfantasy.football.api.ApiService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

import static java.util.Objects.nonNull;

@Service
public class StatKeyService {

  private final ApiService apiService;
  private final StatKeyRepository repository;

  public StatKeyService(final ApiService apiService, final StatKeyRepository repository) {
    this.apiService = apiService;
    this.repository = repository;
  }

  @PostConstruct
  public void populateStatKey() throws IOException {
    var statKeyNodes = apiService.getStatKeys();
    if (nonNull(statKeyNodes)) {
      statKeyNodes.iterator().forEachRemaining(statKeyNode -> {
        var statKey = new StatKey();
        statKey.id = statKeyNode.path("id").asInt();
        statKey.abbr = statKeyNode.path("abbr").textValue();
        statKey.name = statKeyNode.path("name").textValue();
        statKey.shortName = statKeyNode.path("shortName").textValue();
        repository.save(statKey);
      });
    }
  }
}
