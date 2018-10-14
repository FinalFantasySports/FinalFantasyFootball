package com.finalfantasy.football.players.controllers;

import com.finalfantasy.football.players.models.Quarterback;
import com.finalfantasy.football.players.services.QuarterbackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin
@RestController
@RequestMapping(value = "/quarterbacks")
public class QuarterbackController {

  private final QuarterbackService service;

  public QuarterbackController(final QuarterbackService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<Collection<Quarterback>> getQuarterbacksBySeasonAndOrWeek(@RequestParam(required = false) Short season, @RequestParam(required = false) Short week) {
    try {
      return ResponseEntity.ok(service.getQuarterbacksBySeasonAndOrWeek(season, week));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }
  }
}
