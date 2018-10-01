package com.finalfantasy.football.players.controllers;

import com.finalfantasy.football.players.models.WideReceiver;
import com.finalfantasy.football.players.services.WideReceiverService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "/widereceivers")
public class WideReceiverController {

  private final WideReceiverService service;

  public WideReceiverController(final WideReceiverService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<Collection<WideReceiver>> getWideReceiversBySeasonAndOrWeek(@RequestParam(required = false) Short season, @RequestParam(required = false) Short week) {
    return ResponseEntity.ok(service.getWideReceiversBySeasonAndOrWeek(season, week));
  }
}
