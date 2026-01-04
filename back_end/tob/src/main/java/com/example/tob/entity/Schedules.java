package com.example.tob.entity;

import com.example.tob.helpers.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Time;

@Entity
@Table(name = "schedules")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Schedules extends BaseEntity {

    @Id
    @Column(name = "schedules_id", columnDefinition = "BIGINT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schedulesId;

    @Column(name = "venues", columnDefinition = "BIGINT", nullable = false)
    private Long venuesId;

    @Column(name = "schedules_name", length = 300)
    private String schedulesName;

    @Column(name = "schedules_price", columnDefinition = "decimal(12, 2)", nullable = false)
    private BigDecimal schedulesPrice;

    @Column(name = "start_time", nullable = false)
    private Time startTime;

    @Column(name = "end_time", nullable = false)
    private Time endTime;

}
