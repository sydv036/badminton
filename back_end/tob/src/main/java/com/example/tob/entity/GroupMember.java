package com.example.tob.entity;

import com.example.tob.common.enums.GrandRole;
import com.example.tob.helpers.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "group_members")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class GroupMember extends BaseEntity {

    @Id
    @Column(name = "group_member_id", length = 22)
    private String groupMemberId;

    @Column(name = "system_id", nullable = false)
    private String systemId;

    @Column(name = "group_id", length = 22, nullable = false)
    private String groupId;

    @Column(name = "joined_datetime")
    private LocalDateTime joinedDateTime;

    @Column(name = "out_datetime")
    private LocalDateTime outedDateTime;

    @Column(name = "max_number_member")
    private Integer maxNumberMember;

    @Column(name = "role_group")
    private GrandRole grandRole;

}
