package com.example.tob.entity;

import com.example.tob.common.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "m_roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class MRole {

    @Id
    @Column(name = "role_id")
    private String id;

    @Column(name = "code", nullable = false, length = 50, updatable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private RoleEnum code;

    @Column(name = "role_name", nullable = false, length = 100, updatable = false)
    private String roleName;

}
