package com.underlake.honey.repository;

import com.underlake.honey.dao.ArticleEntity;
import com.underlake.honey.dto.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {
    default List<Article> findAllDto() {
        return findAll().stream().map(Article::new).collect(Collectors.toList());
    }
}
