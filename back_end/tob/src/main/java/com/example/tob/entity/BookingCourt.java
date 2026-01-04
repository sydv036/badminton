package com.example.tob.entity;

import com.example.tob.helpers.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Column(name = "booking_id", length = 22)
    private String bookingId;

    @Column(name = "system_id", length = 22, nullable = false)
    private String systemId;

    @Column(name = "court_schedules", length = 22, nullable = false)
    private String courSchedulesId;

    @Column(name = "total_amount", columnDefinition = "decimal(12, 2)", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "start_datetime", nullable = false)
    private LocalDateTime startDateTime;

    @Column(name = "end_datetime", nullable = false)
    private LocalDateTime endDateTime;

    @Column(name = "note", length = 5000)
    private String note;

    @Column(name = "deposits", length = 22, nullable = false)
    private String depositId;

    @Column(name = "deposit_amount", columnDefinition = "decimal(12, 2)", nullable = false)
    private BigDecimal depositAmount;

    @Column(name = "discounts", length = 22)
    private String discountId;

    @Column(name = "discount_amount", columnDefinition = "decimal(12, 2)")
    private BigDecimal discountAmount;
}
