package com.example.tob.entity;

import com.example.tob.helpers.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Column(name = "schedules_id", length = 22)
    private String schedulesId;

    @Column(name = "venues", length = 22, nullable = false)
    private String venuesId;

    @Column(name = "schedules_name", length = 300)
    private String schedulesName;

    @Column(name = "schedules_price", columnDefinition = "decimal(12, 2)", nullable = false)
    private BigDecimal schedulesPrice;

    @Column(name = "start_time", nullable = false)
    private Time startTime;

    @Column(name = "end_time", nullable = false)
    private Time endTime;

}
