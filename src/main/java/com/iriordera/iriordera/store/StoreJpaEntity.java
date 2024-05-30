package com.iriordera.iriordera.store;

import com.iriordera.iriordera.user.UserJpaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Store")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreJpaEntity {

    @Id
    @Column(name = "store_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "table_num", nullable = false)
    private Integer table;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserJpaEntity userJpaEntity;
}
