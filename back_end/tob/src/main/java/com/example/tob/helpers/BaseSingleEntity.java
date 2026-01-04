package com.example.tob.helpers;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;

@MappedSuperclass
public abstract class BaseSingleEntity {

    @Column(nullable = false)
    private boolean systemDeleteFlag;

    @PrePersist
    public void handlerBeforeInsert() {
        systemDeleteFlag = false;
    }

}
