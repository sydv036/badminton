package com.example.tob.entity;

import com.example.tob.helpers.BaseSingleEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "court_schedules")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CourtSchedules extends BaseSingleEntity {

    @Id
    @Column(name = "court_schedules_id", columnDefinition = "BIGINT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courtSchedulesId;

    @Column(name = "courts", columnDefinition = "BIGINT", nullable = false)
    private Long courtId;

    @Column(name = "schedules", columnDefinition = "BIGINT", nullable = false)
    private Long schedulesId;

    @Column(name = "total_amount", columnDefinition = "decimal(12, 2)", nullable = false)
    private BigDecimal totalAmount;

}
