package com.example.tob.entity;

import com.example.tob.helpers.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "courts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Court extends BaseEntity {

    @Id
    @Column(name = "court_id", columnDefinition = "BIGINT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courtId;

    @Column(name = "venues", columnDefinition = "BIGINT", nullable = false)
    private Long venuesId;

    @Column(name = "court_name", length = 100)
    private String courtName;

    @Column(name = "court_amount", columnDefinition = "decimal(12, 2)", nullable = false)
    private BigDecimal courtAmount;

    @Column(name = "description", columnDefinition = "TEXT", length = 2000)
    private String description;

}
