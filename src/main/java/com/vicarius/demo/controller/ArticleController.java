package com.vicarius.demo.controller;

import com.vicarius.demo.model.Article;
import com.vicarius.demo.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    ArticleRepository repo;

    @PostMapping
    public Article create(@RequestBody Article article) {
        return repo.save(article);
    }

    @GetMapping("/author/{author}/tag/{tag}")
    public Iterable<Article> findByAuthorsNameAndFilteredTagQuery(@PathVariable String author, @PathVariable String tag) {
        return repo.findByAuthorsNameAndFilteredTagQuery(author,tag, Pageable.ofSize(10));
    }


    @GetMapping
    public Iterable<Article> listArticle() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Article> listArticleById(@PathVariable String id) {
        return repo.findById(id);
    }

    @PutMapping("/{id}")
    public Article update(@PathVariable String id, @RequestBody Article article) {
        article.setId(id);
        return repo.save(article);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repo.deleteById(id);
    }



}
