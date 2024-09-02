package com.example.onlinevotingsystem.service;

import com.example.onlinevotingsystem.model.VotingItem;
import com.example.onlinevotingsystem.repository.VotingItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VotingItemService {

    @Autowired
    private VotingItemRepository votingItemRepository;

    public List<VotingItem> getAllVotingItems() {
        return votingItemRepository.findAll();
    }

    public VotingItem createVotingItem(VotingItem votingItem) {
        votingItem.setVotes(0);
        return votingItemRepository.save(votingItem);
    }

    public Optional<VotingItem> getVotingItemById(Integer id) {
        return votingItemRepository.findById(id);
    }

    public VotingItem updateVotingItem(Integer id, VotingItem votingItemDetails) {
        Optional<VotingItem> votingItem = votingItemRepository.findById(id);
        if (votingItem.isPresent()) {
            VotingItem existingItem = votingItem.get();
            existingItem.setName(votingItemDetails.getName());
            return votingItemRepository.save(existingItem);
        }
        return null;
    }

    @Transactional
    public List<VotingItem> getAllVotingItemsWithVotes() {
        List<Object[]> results = votingItemRepository.calculateVotesPerItem();
        return results.stream().map(result -> {
            VotingItem item = new VotingItem();
            item.setId((Integer) result[0]);
            item.setName((String) result[1]);
            item.setVotes(((Number) result[2]).intValue());
            return item;
        }).collect(Collectors.toList());
    }
}