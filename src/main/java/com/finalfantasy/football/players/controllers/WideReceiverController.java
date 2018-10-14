package com.finalfantasy.football.players.controllers;

import com.finalfantasy.football.players.models.WideReceiver;
import com.finalfantasy.football.players.services.WideReceiverService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin
@RestController
@RequestMapping(value = "/widereceivers")
public class WideReceiverController {

  private final WideReceiverService service;

  public WideReceiverController(final WideReceiverService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<Collection<WideReceiver>> getWideReceiversBySeasonAndOrWeek(@RequestParam(required = false) Short season, @RequestParam(required = false) Short week) {
    try {
      return ResponseEntity.ok(service.getWideReceiversBySeasonAndOrWeek(season, week));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }
  }
}
