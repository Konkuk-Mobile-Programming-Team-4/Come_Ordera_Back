package com.iriordera.iriordera.menu;

import com.iriordera.iriordera.order.OrderJpaEntity;
import com.iriordera.iriordera.store.StoreJpaEntity;
import com.iriordera.iriordera.user.UserJpaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Menu")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuJpaEntity {

    @Id
    @Column(name = "menu_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "introduction", nullable = false)
    private String introduction;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private StoreJpaEntity storeJpaEntity;
}
