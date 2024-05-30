package com.iriordera.iriordera.order;

import com.iriordera.iriordera.user.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderJpaEntity, Long> {

    Optional<List<OrderJpaEntity>> findAllByStoreJpaEntityId(Long store_id);

    OrderJpaEntity findByIdAndUserJpaEntityId(Long id, Long userId);
}
