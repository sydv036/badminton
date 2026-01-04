package com.example.tob.entity;

import com.example.tob.common.enums.ImageType;
import com.example.tob.helpers.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "images")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Images extends BaseEntity {

    @Id
    @Column(name = "image_id", length = 22)
    private String imageId;

    @Column(name = "image_url", length = 2000)
    private String imageUrl;

    @Column(name = "image_type")
    private ImageType type;

}
