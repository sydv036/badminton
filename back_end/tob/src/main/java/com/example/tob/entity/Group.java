package com.example.tob.entity;

import com.example.tob.helpers.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "groups")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Group extends BaseEntity {

    @Id
    @Column(name = "group_id", length = 22)
    private String groupId;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "descriptions", length = 5000)
    private String descriptions;

    @Column(name = "avatar_url", length = 2000)
    private String avatarUrl;

}
