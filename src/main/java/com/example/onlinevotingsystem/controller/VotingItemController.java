package com.example.onlinevotingsystem.controller;

import com.example.onlinevotingsystem.model.VotingItem;
import com.example.onlinevotingsystem.service.VotingItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voting-items")
@CrossOrigin(origins = "http://localhost:43221")
public class VotingItemController {

    @Autowired
    private VotingItemService votingItemService;

    @GetMapping
    public List<VotingItem> getAllVotingItems() {
        return votingItemService.getAllVotingItemsWithVotes();
    }

    @PostMapping
    public VotingItem createVotingItem(@RequestBody VotingItem votingItem) {
        return votingItemService.createVotingItem(votingItem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VotingItem> getVotingItemById(@PathVariable Integer id) {
        return votingItemService.getVotingItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VotingItem> updateVotingItem(@PathVariable Integer id,
            @RequestBody VotingItem votingItemDetails) {
        VotingItem updatedItem = votingItemService.updateVotingItem(id, votingItemDetails);
        if (updatedItem != null) {
            return ResponseEntity.ok(updatedItem);
        }
        return ResponseEntity.notFound().build();
    }
}