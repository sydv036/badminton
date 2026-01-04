package com.example.tob.entity;

import com.example.tob.common.enums.TransactionType;
import com.example.tob.helpers.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Column(name = "found_transaction_history_id", length = 22)
    private String fundTransactionHistoryId;

    @Column(name = "fund_id", length = 22, nullable = false)
    private String fundId;

    @Column(name = "group_id", length = 22, nullable = false)
    private String groupId;

    @Column(name = "system_id", length = 22, nullable = false)
    private String systemId;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "transaction_datetime")
    private LocalDateTime transactionDateTime;

    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "note", length = 3000)
    private String note;

    @Column(name = "approval_by", nullable = false, length = 22)
    private String approvalBy;

    @Column(name = "approval_at", nullable = false)
    private LocalDateTime approvalAt;

}
