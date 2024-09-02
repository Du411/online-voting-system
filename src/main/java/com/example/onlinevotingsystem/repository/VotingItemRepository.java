package com.example.onlinevotingsystem.repository;

import com.example.onlinevotingsystem.model.VotingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotingItemRepository extends JpaRepository<VotingItem, Integer> {
    @Procedure(procedureName = "calculate_votes_per_item")
    List<Object[]> calculateVotesPerItem();
}