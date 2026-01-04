package com.example.tob.entity;

import com.example.tob.helpers.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;

@Entity
@Table(name = "venues")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Venues extends BaseEntity {

    @Id
    @Column(name = "venues_id", columnDefinition = "BIGINT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long venuesId;

    @Column(name = "system_id", columnDefinition = "BIGINT", nullable = false)
    private Long systemId;

    @Column(name = "venues_name", length = 200, nullable = false)
    private String venuesName;

    @Column(name = "address", length = 1000)
    private String address;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "banner_url", length = 2000, nullable = false)
    private String bannerUrl;

    @Column(name = "images", columnDefinition = "BIGINT")
    private Long imageId;

    @Column(name = "descriptions", length = 5000)
    private String descriptions;

    @Column(name = "operating_hours")
    private Time operatingHours;

    @Column(name = "closing_time")
    private Time closingTime;

}
