package com.example.onlinevotingsystem.controller;

import com.example.onlinevotingsystem.model.Voter;
import com.example.onlinevotingsystem.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/voters")
@CrossOrigin(origins = "http://localhost:43221")
public class VoterController {
    @Autowired
    private VoterService voterService;

    @PostMapping
    public ResponseEntity<?> registerVoter(@RequestBody Map<String, String> payload) {
        try {
            String name = payload.get("name");
            Voter voter = voterService.registerVoter(name);
            return ResponseEntity.ok().body(Map.of("id", voter.getId()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
