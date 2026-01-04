package com.example.tob.entity;

import com.example.tob.helpers.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking_courts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class BookingCourt extends BaseEntity {

    @Id
    @Column(name = "booking_id", columnDefinition = "BIGINT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @Column(name = "system_id", columnDefinition = "BIGINT", nullable = false)
    private Long systemId;

    @Column(name = "court_schedules", columnDefinition = "BIGINT", nullable = false)
    private Long courSchedulesId;

    @Column(name = "total_amount", columnDefinition = "decimal(12, 2)", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "start_datetime", nullable = false)
    private LocalDateTime startDateTime;

    @Column(name = "end_datetime", nullable = false)
    private LocalDateTime endDateTime;

    @Column(name = "note", length = 5000)
    private String note;

    @Column(name = "deposits", columnDefinition = "BIGINT", nullable = false)
    private Long depositId;

    @Column(name = "deposit_amount", columnDefinition = "decimal(12, 2)", nullable = false)
    private BigDecimal depositAmount;

    @Column(name = "discounts", columnDefinition = "BIGINT")
    private Long discountId;

    @Column(name = "discount_amount", columnDefinition = "decimal(12, 2)")
    private BigDecimal discountAmount;
}
