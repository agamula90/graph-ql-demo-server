package com.underlake.honey.service;

import com.underlake.honey.dao.ArticleEntity;
import com.underlake.honey.domain.Article;
import com.underlake.honey.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(
        ArticleRepository articleRepository
    ) {
        this.articleRepository = articleRepository;
    }

    public List<Article> findAllArticles() {
        return articleRepository.findAll().stream().map(this::articleFromEntity).collect(Collectors.toList());
    }

    private Article articleFromEntity(ArticleEntity entity) {
        return new Article(entity.getTitle(), entity.getHref());
    }

    private ArticleEntity entityFromArticle(Article article) {
        ArticleEntity entity = new ArticleEntity();
        entity.setTitle(article.title());
        entity.setHref(article.href());
        return entity;
    }

    public Article articleFromGraphQLInput(Map<String, Object> input) {
        Map<String, Object> articleUnwrapped = (Map<String, Object>) input.get("article");
        ArticleEntity entity = new ArticleEntity();
        Object title = articleUnwrapped.get("title");
        Objects.requireNonNull(title, "Title not supplied");
        Object href = articleUnwrapped.get("href");
        Objects.requireNonNull(href, "Href not supplied");
        if (!(title instanceof String && href instanceof String)) {
            throw new IllegalArgumentException("Some parameter is of incorrect type");
        }
        entity.setTitle(title.toString());
        entity.setHref(href.toString());
        return articleFromEntity(entity);
    }

    public Article addArticle(Article article) {
        ArticleEntity entity = articleRepository.save(entityFromArticle(article));
        return articleFromEntity(entity);
    }

    public boolean deleteArticleById(int id) {
        try {
            ArticleEntity entity = articleRepository.findById(id).orElseThrow();
            articleRepository.delete(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
