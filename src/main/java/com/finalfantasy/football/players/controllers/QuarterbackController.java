package com.finalfantasy.football.players.controllers;

import com.finalfantasy.football.players.models.Quarterback;
import com.finalfantasy.football.players.services.QuarterbackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "/quarterbacks")
public class QuarterbackController {

  private final QuarterbackService service;

  public QuarterbackController(final QuarterbackService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<Collection<Quarterback>> getQuarterbacksBySeasonAndOrWeek(@RequestParam(required = false) Short season, @RequestParam(required = false) Short week) {
    return ResponseEntity.ok(service.getQuarterbacksBySeasonAndOrWeek(season, week));
  }
}
