package com.example.onlinevotingsystem.repository;

import com.example.onlinevotingsystem.model.VotingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotingRecordRepository extends JpaRepository<VotingRecord, Integer> {
    boolean existsByVoterIdAndVotingItemId(Integer voterId, Integer votingItemId);
}