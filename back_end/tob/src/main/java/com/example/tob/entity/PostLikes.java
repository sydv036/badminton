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
    @Column(name = "post_id", columnDefinition = "BIGINT")
    private Long postId;

    @Column(name = "system_id", columnDefinition = "BIGINT", nullable = false)
    private Long system_id;

    @Column(name = "create_at", columnDefinition = "BIGINT")
    private Long createAt;

}
