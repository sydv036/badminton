package com.example.tob.helpers;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    @Column(columnDefinition = "BINARY(16)", nullable = false, unique = true, updatable = false)
    private UUID publicId;

    @Column(nullable = false, updatable = false)
    private String createdBy;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String updatedBy;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private Boolean systemDeleteFlag;

    @PrePersist
    public void handlerBeforeInsert() {
        LocalDateTime now = LocalDateTime.now();
        publicId = UUID.randomUUID();
        createdAt = now;
        updatedAt = now;
        systemDeleteFlag = false;
    }

    @PreUpdate
    public void handlerBeforeUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
