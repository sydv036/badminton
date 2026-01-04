package com.example.tob.entity;

import com.example.tob.common.enums.FundRole;
import com.example.tob.helpers.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "funds")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Fund extends BaseEntity {

    @Id
    @Column(name = "found_id", columnDefinition = "BIGINT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foundId;

    @Column(name = "group_id", columnDefinition = "BIGINT", nullable = false)
    private Long groupId;

    @Column(name = "title")
    private String title;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "init_datetime", nullable = false)
    private LocalDateTime initDateTime;

    @Column(name = "description", length = 5000)
    private String description;

    @Column(name = "fund_role", nullable = false)
    private FundRole fundRole;

}
