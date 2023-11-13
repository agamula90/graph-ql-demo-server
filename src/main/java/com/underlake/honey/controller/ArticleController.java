package com.underlake.honey.controller;

import com.underlake.honey.domain.Article;
import com.underlake.honey.domain.DeleteResult;
import com.underlake.honey.service.ArticleService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.Arguments;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
class ArticleController {

    private final ArticleService articleService;

    ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @QueryMapping
    List<Article> articles() {
        return articleService.findAllArticles();
    }

    @MutationMapping
    Article addArticle(@Arguments Map<String, Object> article) {
        return articleService.addArticle(articleService.articleFromGraphQLInput(article));
    }

    @MutationMapping
    DeleteResult deleteArticle(@Argument int id) {
        boolean isDeleteSucceeded = articleService.deleteArticleById(id);
        return isDeleteSucceeded ? DeleteResult.ok() : DeleteResult.fail();
    }
}
