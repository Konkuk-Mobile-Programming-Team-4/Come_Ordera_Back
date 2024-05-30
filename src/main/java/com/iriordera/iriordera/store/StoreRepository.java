package com.iriordera.iriordera.store;

import com.iriordera.iriordera.user.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<StoreJpaEntity, Long> {

    Optional<StoreJpaEntity> findById(Long id);

    StoreJpaEntity findByUserJpaEntityId (Long user_id);

    List<StoreJpaEntity> findAllByName (String name);

}
