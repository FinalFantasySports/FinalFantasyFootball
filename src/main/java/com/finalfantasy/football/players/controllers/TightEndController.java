package com.finalfantasy.football.players.controllers;

import com.finalfantasy.football.players.models.TightEnd;
import com.finalfantasy.football.players.services.TightEndService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "/tightends")
public class TightEndController {

  private final TightEndService service;

  public TightEndController(final TightEndService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<Collection<TightEnd>> getTightEndsBySeasonAndOrWeek(@RequestParam(required = false) Short season, @RequestParam(required = false) Short week) {
    return ResponseEntity.ok(service.getTightEndsBySeasonAndOrWeek(season, week));
  }
}
