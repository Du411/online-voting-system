package com.example.onlinevotingsystem.service;

import com.example.onlinevotingsystem.model.Voter;
import com.example.onlinevotingsystem.repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoterService {

    @Autowired
    private VoterRepository voterRepository;

    public Voter registerVoter(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }
        Voter voter = new Voter();
        voter.setName(name.trim());
        return voterRepository.save(voter);
    }
}