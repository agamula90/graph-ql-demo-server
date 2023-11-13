package com.underlake.honey.controller;

import com.underlake.honey.domain.DeleteResult;
import com.underlake.honey.domain.SupplementaryContentItem;
import com.underlake.honey.service.ContentItemService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.Arguments;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
class SupplementaryContentItemController {

    private final ContentItemService contentItemService;

    SupplementaryContentItemController(ContentItemService contentItemService) {
        this.contentItemService = contentItemService;
    }

    @QueryMapping
    List<SupplementaryContentItem> supplementaryContentItems() {
        return contentItemService.findSupplementaryContentItems();
    }

    @MutationMapping
    SupplementaryContentItem addSupplementaryContentItem(@Arguments Map<String, Object> article) {
        return contentItemService.addSupplementaryContentItem(contentItemService.supplementaryContentItemFromGraphQLInput(article));
    }

    @MutationMapping
    DeleteResult deleteSupplementaryContentItem(@Argument int id) {
        boolean isDeleteSucceeded = contentItemService.deleteContentItemById(id);
        return isDeleteSucceeded ? DeleteResult.ok() : DeleteResult.fail();
    }
}
