package com.example.tob.entity;

import com.example.tob.common.enums.ContributionStatus;
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
@Table(name = "contributions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Contribution extends BaseEntity {

    @Id
    @Column(name = "contribution_id", length = 22)
    private String contributionId;

    @Column(name = "group_id", length = 22)
    private String groupId;

    @Column(name = "system_id", length = 22)
    private String systemId;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "transaction_datetime")
    private LocalDateTime transactionDatetime;

    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "approval_by", length = 22)
    private String approvalBy;

    @Column(name = "approval_at")
    private LocalDateTime approvalAt;

    @Column(name = "contribution_status")
    private ContributionStatus contributionStatus;

    @Column(name = "note", length = 2000)
    private String note;

}
