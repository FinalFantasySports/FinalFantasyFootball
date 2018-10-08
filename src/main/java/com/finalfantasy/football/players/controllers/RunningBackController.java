package com.finalfantasy.football.players.controllers;

import com.finalfantasy.football.players.models.RunningBack;
import com.finalfantasy.football.players.services.RunningBackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "/runningbacks")
public class RunningBackController {

  private static final Logger log = LoggerFactory.getLogger(RunningBackController.class);

  private final RunningBackService service;

  public RunningBackController(final RunningBackService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<Collection<RunningBack>> getRunningBackBySeasonAndOrWeek(@RequestParam(required = false) Short season, @RequestParam(required = false) Short week) {
    try {
      return ResponseEntity.ok(service.getRunningBacksBySeasonAndOrWeek(season,week ));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }
  }
}
