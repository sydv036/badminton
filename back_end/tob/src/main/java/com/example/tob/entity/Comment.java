package com.example.tob.entity;

import com.example.tob.helpers.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Comment extends BaseEntity {

    @Id
    @Column(name = "comment_id", columnDefinition = "BIGINT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String commentId;

    @Column(name = "system_id", columnDefinition = "BIGINT")
    private Long systemId;

    @Column(name = "content", length = 5000)
    private String content;

    @Column(name = "like_count")
    private Integer likeCount;

}
