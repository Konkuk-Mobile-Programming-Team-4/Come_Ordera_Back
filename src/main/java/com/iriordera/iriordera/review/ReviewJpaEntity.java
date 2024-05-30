package com.iriordera.iriordera.review;

import com.iriordera.iriordera.menu.MenuJpaEntity;
import com.iriordera.iriordera.store.StoreJpaEntity;
import com.iriordera.iriordera.user.UserJpaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Review")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewJpaEntity {

    @Id
    @Column(name = "review_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "star", nullable = false)
    private Integer star;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserJpaEntity userJpaEntity;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private StoreJpaEntity storeJpaEntity;
}
