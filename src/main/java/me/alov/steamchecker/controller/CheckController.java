package me.alov.steamchecker.controller;

import me.alov.steamchecker.model.Info;
import me.alov.steamchecker.service.SteamCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/check")
public class CheckController {

    @Autowired
    private SteamCheckService steamCheckService;

    @GetMapping
    public ResponseEntity<Info> checkOnline(@RequestParam String steamId) {
        Info info = steamCheckService.checkSingleClient(steamId);
        return ResponseEntity.ok(info);
    }


    @GetMapping("/mass")
    public ResponseEntity<List<Info>> checkMass(@RequestParam String steamIds) {
        List<Info> infos = steamCheckService.massCheck(steamIds);
        return ResponseEntity.ok(infos);
    }
}
