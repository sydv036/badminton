package com.example.tob.entity;


import com.example.tob.helpers.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Account extends BaseEntity {

    @Id
    @Column(name = "system_id", length = 22)
    private String systemId;

    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String userName;

    @Column(unique = true, nullable = false, length = 5000)
    private String password;

    @Column(name = "last_login_datetime")
    private LocalDateTime lastLoginDateTime;

    @Column(name = "lock", nullable = false)
    private Boolean locked;

    @Column(name = "active", nullable = false)
    private Boolean active;

}
