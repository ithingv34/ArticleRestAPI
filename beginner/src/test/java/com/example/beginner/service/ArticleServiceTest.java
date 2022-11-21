package com.example.beginner.service;

import com.example.beginner.dto.ArticleForm;
import com.example.beginner.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 해당 클래스는 스프링부트와 연동되어 테스트된다.
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;

    @Test
    void index() {
        // 예상

        Article a = new Article(1L, "1", "first content");
        Article b = new Article(2L, "2", "second content");
        Article c = new Article(3L, "3", "third content");

        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));

        // 실제
        List<Article> articles = articleService.index();

        // 비교
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_성공() {

        // 예상
        Long id = 1L;
        Article expected = new Article(id, "1", "first content");

        // 실제
        Article article = articleService.show(id);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_실패_존재하지_않는_id() {
        // 예상
        Long id = -1L;
        Article expected = null;

        // 실제
        Article article = articleService.show(id);

        // 비교
        assertEquals(expected, article);

    }

    @Test
    @Transactional
    void create_성공_title과_context만_있는_dto_입력(){
        // 예상
        String title = "";
        String content = "";
        ArticleForm dto = new ArticleForm(null, title, content);

        Article expected = new Article(4L, title, content);

        // 실제
        Article article = articleService.create(dto);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void create_실패__id가_포함된_dto_입력(){
        // 예상
        String title = "";
        String content = "";
        ArticleForm dto = new ArticleForm(-1L, title, content);

        Article expected = null;

        // 실제
        Article article = articleService.create(dto);

        // 비교
        assertEquals(expected, article);
    }


    // 업데이트
    @Test
    @Transactional
    void update_성공__존재하는_id와_title만_있는_dto_입력(){

    }

    @Test
    @Transactional
    void update_실패_존재하지_않는_id와_dto_입력(){

    }

    @Test
    @Transactional
    void update_실패__id만_있는_dto_입력(){

    }

    @Test
    @Transactional
    void delete_성공__존재하는_id_입력(){

    }

    @Test
    @Transactional
    void delete_실패__존재하지_않는_id_입력(){

    }


}