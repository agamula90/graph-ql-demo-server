package com.underlake.honey.controller;

import com.underlake.honey.dao.ContentEntity;
import com.underlake.honey.dto.DeleteResult;
import com.underlake.honey.dto.SupplementaryItem;
import com.underlake.honey.repository.ContentItemRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.Arguments;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
public class SupplementaryContentItemController {

    private final ContentItemRepository repository;

    public SupplementaryContentItemController(ContentItemRepository repository) {
        this.repository = repository;
    }

    @QueryMapping
    public List<SupplementaryItem> supplementaryContentItems() {
        return repository.findSupplementaryContentItems();
    }

    @MutationMapping
    SupplementaryItem addSupplementaryContentItem(@Arguments Map<String, Object> article) {
        ContentEntity entity = repository.save(ContentEntity.supplementaryContentItemFromGraphQLInput(article));
        return new SupplementaryItem(entity);
    }

    @MutationMapping
    DeleteResult deleteSupplementaryContentItem(@Argument int id) {
        try {
            repository.deleteById(id);
            return DeleteResult.ok();
        } catch (Exception e) {
            return DeleteResult.fail();
        }
    }
}
