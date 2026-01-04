package com.example.tob.entity;

import com.example.tob.common.enums.GenderEnum;
import com.example.tob.helpers.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "members")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Member extends BaseEntity {

    @Id
    @Column(name = "system_id", columnDefinition = "BIGINT")
    private Long systemId;

    @Column(name = "email", nullable = false, unique = true, length = 300)
    private String email;

    @Column(name = "username", unique = true, nullable = false, length = 100)
    private String userName;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "gender")
    private GenderEnum gender;

    @Column(name = "avatar_url", length = 2000)
    private String avatarUrl;

}
