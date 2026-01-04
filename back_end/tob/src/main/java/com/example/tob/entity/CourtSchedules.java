package com.example.tob.entity;

import com.example.tob.helpers.BaseSingleEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Column(name = "court_schedules_id", length = 22)
    private String courtSchedulesId;

    @Column(name = "courts", length = 22, nullable = false)
    private String courtId;

    @Column(name = "schedules", length = 22, nullable = false)
    private String schedulesId;

    @Column(name = "total_amount", columnDefinition = "decimal(12, 2)", nullable = false)
    private BigDecimal totalAmount;

}
