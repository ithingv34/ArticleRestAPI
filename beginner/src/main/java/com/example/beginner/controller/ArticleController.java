package com.example.beginner.controller;

import com.example.beginner.dto.ArticleCreateDto;
import com.example.beginner.entity.Article;
import com.example.beginner.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ArticleController {

    @Autowired // 스프링 부트가 미리 생성해놓은 객체를 가지고 자동 연결
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("articles/create")
    public String createArticle(ArticleCreateDto form){
        log.info(form.toString());

        // dto -> entity
        Article article = form.toEntity();
        log.info(article.toString());

        // repository -> entity를 db에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());

        return "";
    }
}
