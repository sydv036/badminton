package com.example.tob.entity;

import com.example.tob.common.enums.PaymentStatus;
import com.example.tob.helpers.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "bill_detail")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class BillDetail extends BaseEntity {

    @Id
    @Column(name = "bill_id", columnDefinition = "BIGINT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billId;

    @Column(name = "user_payment", length = 200)
    private String userPayment;

    @Column(name = "system_id", columnDefinition = "BIGINT")
    private String systemId;

    @Column(name = "amount_deposit")
    private BigDecimal amountDeposit;

    @Column(name = "booking_court_id", columnDefinition = "BIGINT")
    private Long bookingCourtId;

    @Column(name = "booking_service_id", columnDefinition = "BIGINT")
    private Long bookingServiceId;

    @Column(name = "drinks_id", columnDefinition = "BIGINT")
    private Long drinksServiceId;

    @Column(name = "amount", columnDefinition = "decimal(12, 2)", nullable = false)
    private BigDecimal amount;

    @Column(name = "note", length = 5000)
    private String note;

    @Column(name = "status", nullable = false)
    private PaymentStatus status;

}
