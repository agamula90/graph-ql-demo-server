package com.underlake.honey.controller;

import com.underlake.honey.dao.ContentEntity;
import com.underlake.honey.dto.ContentItem;
import com.underlake.honey.dto.DeleteResult;
import com.underlake.honey.repository.ContentItemRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.Arguments;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
public class ContentItemController {

    private final ContentItemRepository repository;

    public ContentItemController(ContentItemRepository repository) {
        this.repository = repository;
    }

    @QueryMapping
    public List<ContentItem> contentItems() {
        return repository.findContentItems();
    }

    @MutationMapping
    ContentItem addContentItem(@Arguments Map<String, Object> article) {
        ContentEntity entity = repository.save(ContentEntity.contentItemFromGraphQLInput(article));
        return new ContentItem(entity);
    }

    @MutationMapping
    DeleteResult deleteContentItem(@Argument int id) {
        try {
            repository.deleteById(id);
            return DeleteResult.ok();
        } catch (Exception e) {
            return DeleteResult.fail();
        }
    }
}
