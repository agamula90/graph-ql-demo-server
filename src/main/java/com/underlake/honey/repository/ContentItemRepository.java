package com.underlake.honey.repository;

import com.underlake.honey.dao.ContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentItemRepository extends JpaRepository<ContentEntity, Integer> {

    List<ContentEntity> findContentEntitiesByIsSupplementaryFalse();
    List<ContentEntity> findContentEntitiesByIsSupplementaryTrue();
}
