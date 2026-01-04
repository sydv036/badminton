package com.example.tob.entity;

import com.example.tob.helpers.BaseSingleEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "comment_likes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CommentLikes extends BaseSingleEntity {

    @Id
    @Column(name = "comment_id", length = 22)
    private String commentId;

    @Column(name = "system_id", length = 22, nullable = false)
    private String system_id;

    @Column(name = "create_at", length = 22)
    private String createAt;

}
