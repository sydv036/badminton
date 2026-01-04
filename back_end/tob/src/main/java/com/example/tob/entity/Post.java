package com.example.tob.entity;


import com.example.tob.helpers.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Column(name = "post_id", length = 22)
    private String postId;

    @Column(name = "system_id", length = 22)
    private String systemId;

    @Column(name = "title", length = 200)
    private String title;

    @Column(name = "content", length = 5000)
    private String content;

    @Column(name = "imagesId", length = 22)
    private String images;

    @Column(name = "featured_image", length = 5000)
    private String featuredImage;

    @Column(name = "like_count")
    private int likeCount;

    @Column(name = "published_at", length = 22)
    private String publishedAt;

}
