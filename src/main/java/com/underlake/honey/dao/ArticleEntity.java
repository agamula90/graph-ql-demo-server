package com.underlake.honey.dao;

import jakarta.persistence.*;

@Entity
@Table(name = "article")
public class ArticleEntity {

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
