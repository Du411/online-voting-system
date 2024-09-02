package com.example.onlinevotingsystem.service;

import com.example.onlinevotingsystem.model.VotingRecord;
import com.example.onlinevotingsystem.repository.VotingRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VotingRecordService {

    @Autowired
    private VotingRecordRepository votingRecordRepository;

    @Transactional
    public void vote(Integer voterId, Integer votingItemId) {
        if (votingRecordRepository.existsByVoterIdAndVotingItemId(voterId, votingItemId)) {
            throw new IllegalStateException("您已經為這個項目投過票了");
        }

        VotingRecord votingRecord = new VotingRecord();
        votingRecord.setVoterId(voterId);
        votingRecord.setVotingItemId(votingItemId);
        votingRecordRepository.save(votingRecord);
    }

    public boolean hasVoted(Integer userId, Integer itemId) {
        return votingRecordRepository.existsByVoterIdAndVotingItemId(userId, itemId);
    }
}