package com.iriordera.iriordera.order;

import com.iriordera.iriordera.menu.MenuJpaEntity;
import com.iriordera.iriordera.store.StoreJpaEntity;
import com.iriordera.iriordera.user.UserJpaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderJpaEntity {

    @Id
    @Column(name = "order_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "table_number", nullable = false)
    private Integer table_number;

    @ElementCollection
    private List<Long> menuIdList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserJpaEntity userJpaEntity;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private StoreJpaEntity storeJpaEntity;
}
