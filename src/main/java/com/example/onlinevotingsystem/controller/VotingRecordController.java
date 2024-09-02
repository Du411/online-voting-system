package com.example.onlinevotingsystem.controller;

import com.example.onlinevotingsystem.service.VotingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/voting-records")
@CrossOrigin(origins = "http://localhost:43221")
public class VotingRecordController {

    @Autowired
    private VotingRecordService votingRecordService;

    @PostMapping
    public ResponseEntity<?> vote(@RequestParam String voterId, @RequestParam Integer votingItemId) {
        try {
            Integer voterIdInt = Integer.parseInt(voterId);
            votingRecordService.vote(voterIdInt, votingItemId);
            return ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("投票失敗");
        }
    }

    @GetMapping("/has-voted/{userId}/{itemId}")
    public ResponseEntity<Boolean> hasVoted(@PathVariable String userId, @PathVariable Integer itemId) {
        try {
            Integer userIdInt = Integer.parseInt(userId);
            boolean hasVoted = votingRecordService.hasVoted(userIdInt, itemId);
            return ResponseEntity.ok(hasVoted);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(false);
        }
    }
}