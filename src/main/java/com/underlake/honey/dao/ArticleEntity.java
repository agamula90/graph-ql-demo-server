package com.underlake.honey.dao;

import jakarta.persistence.*;

import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "article")
public class ArticleEntity {

    public static ArticleEntity fromGraphQLInput(Map<String, Object> input) {
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
        return entity;
    }

    @Id
    @SequenceGenerator(name = "article_gen", sequenceName = "article_seq", allocationSize = 1)
    @GeneratedValue(generator = "article_gen", strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String href;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
