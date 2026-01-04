package com.example.tob.entity;

import com.example.tob.helpers.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "members")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Court extends BaseEntity {

    @Id
    @Column(name = "court_id", length = 22)
    private String courtId;

    @Column(name = "venues", length = 22, nullable = false)
    private String venuesId;

    @Column(name = "court_name", length = 100)
    private String courtName;

    @Column(name = "court_amount", columnDefinition = "decimal(12, 2)", nullable = false)
    private BigDecimal courtAmount;

    @Column(name = "description", columnDefinition = "TEXT", length = 2000)
    private String description;

}
