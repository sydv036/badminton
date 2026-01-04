package com.example.tob.entity;

import com.example.tob.common.enums.TransactionType;
import com.example.tob.helpers.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "fund_transaction_historys")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class FundTransactionHistory extends BaseEntity {

    @Id
    @Column(name = "found_transaction_history_id", columnDefinition = "BIGINT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fundTransactionHistoryId;

    @Column(name = "fund_id", columnDefinition = "BIGINT", nullable = false)
    private Long fundId;

    @Column(name = "group_id", columnDefinition = "BIGINT", nullable = false)
    private Long groupId;

    @Column(name = "system_id", columnDefinition = "BIGINT", nullable = false)
    private Long systemId;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "transaction_datetime")
    private LocalDateTime transactionDateTime;

    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "note", length = 3000)
    private String note;

    @Column(name = "approval_by", nullable = false, columnDefinition = "BIGINT")
    private Long approvalBy;

    @Column(name = "approval_at", nullable = false)
    private LocalDateTime approvalAt;

}
