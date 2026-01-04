package com.example.tob.entity;

import com.example.tob.helpers.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "`groups`")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Group extends BaseEntity {

    @Id
    @Column(name = "group_id", columnDefinition = "BIGINT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "descriptions",columnDefinition = "TEXT")
    private String descriptions;

    @Column(name = "avatar_url", length = 2000)
    private String avatarUrl;

}
