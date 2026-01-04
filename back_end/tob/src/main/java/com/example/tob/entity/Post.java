package com.example.tob.entity;


import com.example.tob.helpers.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "posts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Post extends BaseEntity {

    @Id
    @Column(name = "post_id", columnDefinition = "BIGINT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(name = "system_id", columnDefinition = "BIGINT")
    private Long systemId;

    @Column(name = "title", length = 200)
    private String title;

    @Column(name = "content", length = 5000)
    private String content;

    @Column(name = "imagesId", columnDefinition = "BIGINT")
    private Long images;

    @Column(name = "featured_image", length = 5000)
    private String featuredImage;

    @Column(name = "like_count")
    private int likeCount;

    @Column(name = "published_at", columnDefinition = "BIGINT")
    private Long publishedAt;

}
