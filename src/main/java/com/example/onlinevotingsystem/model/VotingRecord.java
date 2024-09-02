package com.example.onlinevotingsystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "voting_records", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "voter_id", "voting_item_id" })
})
public class VotingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "voter_id", nullable = false)
    private Integer voterId;

    @Column(name = "voting_item_id", nullable = false)
    private Integer votingItemId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVoterId() {
        return voterId;
    }

    public void setVoterId(Integer voterId) {
        this.voterId = voterId;
    }

    public Integer getVotingItemId() {
        return votingItemId;
    }

    public void setVotingItemId(Integer votingItemId) {
        this.votingItemId = votingItemId;
    }
}