package com.example.tob.entity;

import com.example.tob.helpers.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Column(name = "comment_id", length = 22)
    private String commentId;

    @Column(name = "system_id", length = 22)
    private String systemId;

    @Column(name = "content", length = 5000)
    private String content;

    @Column(name = "like_count")
    private Integer likeCount;

}
