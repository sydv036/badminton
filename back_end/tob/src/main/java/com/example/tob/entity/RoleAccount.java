package com.example.tob.entity;

import com.example.tob.common.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "role_accounts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class RoleAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "system_id", nullable = false, columnDefinition = "BIGINT")
    private Long systemId;

    @Column(name = "role_id", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum roleId;

}
