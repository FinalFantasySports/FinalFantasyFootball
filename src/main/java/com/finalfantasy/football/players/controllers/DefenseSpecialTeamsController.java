package com.finalfantasy.football.players.controllers;

import com.finalfantasy.football.players.models.DefenseSpecialTeams;
import com.finalfantasy.football.players.services.DefenseSpecialTeamsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "/defensespecialteamss")
public class DefenseSpecialTeamsController {

  private final DefenseSpecialTeamsService service;

  public DefenseSpecialTeamsController(final DefenseSpecialTeamsService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<Collection<DefenseSpecialTeams>> getDefenseSpecialTeamssBySeasonAndOrWeek(@RequestParam(required = false) Short season, @RequestParam(required = false) Short week) {
    return ResponseEntity.ok(service.getDefenseSpecialTeamssBySeasonAndOrWeek(season, week ));
  }

}
