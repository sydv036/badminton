package com.example.tob.entity;

import com.example.tob.helpers.BaseEntity;
import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long depositId;

    @Column(name = "precent", nullable = false)
    private Integer percent;

}
