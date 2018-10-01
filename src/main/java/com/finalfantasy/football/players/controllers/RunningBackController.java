package com.finalfantasy.football.players.controllers;

import com.finalfantasy.football.players.models.RunningBack;
import com.finalfantasy.football.players.services.RunningBackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "/runningbacks")
public class RunningBackController {

  private final RunningBackService service;

  public RunningBackController(final RunningBackService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<Collection<RunningBack>> getRunningBackBySeasonAndOrWeek(@RequestParam(required = false) Short season, @RequestParam(required = false) Short week) {
    return ResponseEntity.ok(service.getRunningBacksBySeasonAndOrWeek(season, week));
  }
}
