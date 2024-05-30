package com.iriordera.iriordera.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<MenuJpaEntity, Long> {
    List<MenuJpaEntity> findAllByStoreJpaEntityId(Long storeId);
}
