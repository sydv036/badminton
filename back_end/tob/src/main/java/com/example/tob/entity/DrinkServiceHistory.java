package com.example.tob.entity;

import com.example.tob.common.enums.DrinkServiceType;
import com.example.tob.helpers.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "drinks_service_history")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class DrinkServiceHistory extends BaseEntity {

    @Id
    @Column(name = "drinks_id", length = 22)
    private String drinksId;

    @Column(name = "venues_id", length = 22)
    private String venuesId;

    @Column(name = "drinks_name", length = 300)
    private String drinksName;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "type")
    private DrinkServiceType type;

    @Column(name = "delivery_time", nullable = false)
    private LocalDateTime deliveryDateTime;

}
