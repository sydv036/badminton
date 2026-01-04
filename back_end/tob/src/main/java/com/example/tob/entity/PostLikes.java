package com.example.tob.entity;

import com.example.tob.helpers.BaseSingleEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "post_likes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class PostLikes extends BaseSingleEntity {

    @Id
    @Column(name = "post_id", length = 22)
    private String postId;

    @Column(name = "system_id", length = 22, nullable = false)
    private String system_id;

    @Column(name = "create_at", length = 22)
    private String createAt;

}
