package com.example.tob.entity;

import com.example.tob.common.enums.DrinkServiceType;
import com.example.tob.helpers.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "booking_services")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class BookingService extends BaseEntity {

    @Id
    @Column(name = "booking_id", columnDefinition = "BIGINT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @Column(name = "drinks_id", columnDefinition = "BIGINT", nullable = false)
    private Long drinksServiceId;

    @Column(name = "system_id", columnDefinition = "BIGINT", nullable = false)
    private Long systemId;

    @Column(name = "total_amount", columnDefinition = "decimal(12, 2)", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "service_type")
    private DrinkServiceType type;

    @Column(name = "discounts", columnDefinition = "BIGINT")
    private Long discountId;

    @Column(name = "discount_amount", columnDefinition = "decimal(12, 2)")
    private String discountAmount;

}
