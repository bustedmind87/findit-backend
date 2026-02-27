package com.school.lostfound.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "items")
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Long categoryId;

    private String categoryName;

    private String location;

    private String dateFound; // ISO date string

    @Enumerated(EnumType.STRING)
    private ItemType type = ItemType.FOUND;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    private String priorityFlags;

    private String reporterContact;

    private String retentionExpiry;

    private String createdAt;

    public enum ItemType {FOUND, LOST}

    public enum Status {PENDING, APPROVED, CLAIMED, RETURNED, REJECTED}
}
