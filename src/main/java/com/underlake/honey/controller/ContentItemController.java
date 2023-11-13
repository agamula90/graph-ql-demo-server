package com.underlake.honey.controller;

import com.underlake.honey.domain.ContentItem;
import com.underlake.honey.domain.DeleteResult;
import com.underlake.honey.service.ContentItemService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.Arguments;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
class ContentItemController {

    private final ContentItemService contentItemService;

    ContentItemController(ContentItemService contentItemService) {
        this.contentItemService = contentItemService;
    }

    @QueryMapping
    List<ContentItem> contentItems() {
        return contentItemService.findContentItems();
    }

    @MutationMapping
    ContentItem addContentItem(@Arguments Map<String, Object> contentItem) {
        return contentItemService.addContentItem(contentItemService.contentItemFromGraphQLInput(contentItem));
    }

    @MutationMapping
    DeleteResult deleteContentItem(@Argument int id) {
        boolean isDeleteSucceeded = contentItemService.deleteContentItemById(id);
        return isDeleteSucceeded ? DeleteResult.ok() : DeleteResult.fail();
    }
}
