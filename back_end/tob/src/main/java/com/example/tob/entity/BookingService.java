package com.example.tob.entity;

import com.example.tob.common.enums.DrinkServiceType;
import com.example.tob.helpers.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Column(name = "booking_id", length = 22)
    private String bookingId;

    @Column(name = "drinks_id", length = 22, nullable = false)
    private String drinksServiceId;

    @Column(name = "system_id", length = 22, nullable = false)
    private String systemId;

    @Column(name = "total_amount", columnDefinition = "decimal(12, 2)", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "service_type")
    private DrinkServiceType type;

    @Column(name = "discounts", length = 22)
    private String discountId;

    @Column(name = "discount_amount", columnDefinition = "decimal(12, 2)")
    private String discountAmount;

}
