package com.school.lostfound.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "claims")
@Data
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long itemId;
    private String claimantName;
    private String claimantContact;

    @Column(columnDefinition = "TEXT")
    private String message;
    private String proofUrl;
    @Enumerated(EnumType.STRING)
    private ClaimStatus status = ClaimStatus.PENDING;
    private String createdAt;
    public enum ClaimStatus { PENDING, APPROVED, REJECTED }
}
