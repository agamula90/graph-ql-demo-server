package com.underlake.honey.controller;

import com.underlake.honey.dao.ArticleEntity;
import com.underlake.honey.dto.Article;
import com.underlake.honey.dto.DeleteResult;
import com.underlake.honey.repository.ArticleRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.Arguments;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
public class ArticleController {

    private final ArticleRepository repository;

    ArticleController(ArticleRepository repository) {
        this.repository = repository;
    }

    @QueryMapping
    List<Article> articles() {
        return repository.findAllDto();
    }

    @MutationMapping
    Article addArticle(@Arguments Map<String, Object> article) {
        ArticleEntity entity = repository.save(ArticleEntity.fromGraphQLInput(article));
        return new Article(entity);
    }

    @MutationMapping
    DeleteResult deleteArticle(@Argument int id) {
        try {
            repository.deleteById(id);
            return DeleteResult.ok();
        } catch (Exception e) {
            return DeleteResult.fail();
        }
    }
}
