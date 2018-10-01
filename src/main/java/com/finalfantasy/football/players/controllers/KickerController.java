package com.finalfantasy.football.players.controllers;

import com.finalfantasy.football.players.models.Kicker;
import com.finalfantasy.football.players.services.KickerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "/kickers")
public class KickerController {

  private final KickerService service;

  public KickerController(final KickerService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<Collection<Kicker>> getKickersBySeasonAndOrWeek(@RequestParam(required = false) Short season, @RequestParam(required = false) Short week) {
    return ResponseEntity.ok(service.getKickersBySeasonAndOrWeek(season, week));
  }
}
