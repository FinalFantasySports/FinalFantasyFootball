package com.finalfantasy.football.players.controllers;

import com.finalfantasy.football.players.models.Kicker;
import com.finalfantasy.football.players.services.KickerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin
@RestController
@RequestMapping(value = "/kickers")
public class KickerController {

  private final KickerService service;

  public KickerController(final KickerService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<Collection<Kicker>> getKickersBySeasonAndOrWeek(@RequestParam(required = false) Short season, @RequestParam(required = false) Short week) {
    try {
      return ResponseEntity.ok(service.getKickersBySeasonAndOrWeek(season, week));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }
  }
}
