package com.underlake.honey.repository;

import com.underlake.honey.dao.ContentEntity;
import com.underlake.honey.dto.SupplementaryItem;
import com.underlake.honey.dto.ContentItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface ContentItemRepository extends JpaRepository<ContentEntity, Integer> {

    List<ContentEntity> findContentEntitiesByIsSupplementaryFalse();
    List<ContentEntity> findContentEntitiesByIsSupplementaryTrue();

    default List<ContentItem> findContentItems() {
        List<ContentEntity> contentItems = findContentEntitiesByIsSupplementaryFalse();
        return contentItems.stream().map(ContentItem::new).collect(Collectors.toList());
    }

    default List<SupplementaryItem> findSupplementaryContentItems() {
        List<ContentEntity> contentItems = findContentEntitiesByIsSupplementaryTrue();
        return contentItems.stream().map(SupplementaryItem::new).collect(Collectors.toList());
    }
}
