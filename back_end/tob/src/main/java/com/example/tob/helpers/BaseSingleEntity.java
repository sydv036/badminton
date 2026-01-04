package com.example.tob.helpers;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;

import java.util.UUID;

@MappedSuperclass
public abstract class BaseSingleEntity {

    @Column(nullable = false, unique = true, updatable = false)
    private UUID publicId;

    @Column(nullable = false)
    private boolean systemDeleteFlag;

    @PrePersist
    public void handlerBeforeInsert() {
        publicId = UUID.randomUUID();
        systemDeleteFlag = false;
    }

}
