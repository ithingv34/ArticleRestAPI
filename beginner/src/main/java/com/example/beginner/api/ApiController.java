package com.example.beginner.api;

import com.example.beginner.dto.ArticleCreateDto;
import com.example.beginner.dto.ArticleUpdateDto;
import com.example.beginner.entity.Article;
import com.example.beginner.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController // Json을 반환하는 컨트롤러
public class ApiController {

    @Autowired
    private ArticleRepository articleRepository;
    // GET
    @GetMapping("/api/articles")
    public List<Article> findArticles(){
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article findArticle(@PathVariable Long id){
        return articleRepository.findById(id).orElse(null);
    }

    // POST
    @PostMapping("/api/articles")
    public Article createArticle(@RequestBody ArticleCreateDto dto){
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    // PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id,
                                        @RequestBody ArticleUpdateDto dto) {

    // 1. 수정용 엔티티 생성
    Article article = dto.toEntity();
    log.info("id: {}, article: {}", id, article.toString());

    // 2. 대상 엔티티를 조회
    Article target = articleRepository.findById(id).orElse(null);

    // 3. 잘못된 요청 처리 (대상이 없거나, id가 다른 경우)
    if (target == null || id != article.getId()) {
        // 400, 잘못된 응답
        log.info("잘못된 요청 id: {}, article: {}", id, article.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    // 4. 업데이트 및 정상 응답(200)
    target.patch(article);
    Article updated = articleRepository.save(target);
    return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> deleteArticle(@PathVariable Long id) {
        Article target = articleRepository.findById(id).orElse(null);

        // 대상이 존재하지 않는 경우
        if (target == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 대상 삭제
        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
