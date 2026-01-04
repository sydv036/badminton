package com.example.tob.entity;

import com.example.tob.helpers.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "deposits")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Deposit extends BaseEntity {

    @Id
    @Column(name = "deposit_id")
    private String depositId;

    @Column(name = "precent", nullable = false)
    private Integer percent;

}
