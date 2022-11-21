package com.example.beginner.service;

import com.example.beginner.dto.ArticleForm;
import com.example.beginner.entity.Article;
import com.example.beginner.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();

        if (article.getId() != null) {
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {

        // 1. 수정용 엔티티 생성
        Article article = dto.toEntity();

        // 2. 대상 엔티티를 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리 (대상이 없거나, id가 다른 경우)
        if (target == null || id != article.getId()) {
            // 400, 잘못된 응답
            log.info("잘못된 요청 id: {}, article: {}", id, article.toString());
            return null;
        }

        // 4. 업데이트 및 정상 응답(200)
        target.patch(article);

        Article updated = articleRepository.save(target);
        return updated;
    }

    public Article delete(Long id) {
        // 1. 대상 엔티티 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // 대상이 존재하지 않는 경우
        if (target == null) {
            return null;
        }
        // 대상 삭제
        articleRepository.delete(target);
        return target;
    }

    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // dto 묶음을 entity 묶음으로 변환
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());

        // entity 묶음을 db로 저장
        articleList.stream()
                .forEach(article -> articleRepository.save(article));

        // 강제 예외 발생
        articleRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("강제 실패")
        );
        // 결과값 반환

        return articleList;
    }
}
