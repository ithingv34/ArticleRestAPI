package com.example.beginner.repository;

import com.example.beginner.entity.Article;
import com.example.beginner.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        /* Case 1: 4번 게시글의 모든 댓글 조회 */
        {
            // 준비
            Long articleId = 4L;
            // 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 예상
            Article article = new Article(4L, "java programming", "java programming");
            Comment a = new Comment(1L, article, "noop1", "Java1");
            Comment b = new Comment(2L, article, "noop2", "Java2");
            Comment c = new Comment(3L, article, "noop3", "Java3");
            List<Comment> expected = Arrays.asList(a, b, c);
            // 검증
            assertEquals(expected.toString(), comments.toString());
        }
        /* Case 2: 1번 게시글의 모든 댓글 조회 */
        {
            // 준비
            Long articleId = 1L;
            // 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 예상
            Article article = new Article(1L, "1", "first content");
            List<Comment> expected = Arrays.asList();
            // 검증
            assertEquals(expected.toString(), comments.toString(), "1번 글은 댓글이 없음");
        }
    }


    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        /* Case 1: "Park"의 모든 댓글 조회 */
        {
            // 준비
            String nickname = "noop3";
            // 수행
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 예상
            Comment a = new Comment(3L, new Article(4L, "java programming", "java programming"), nickname, "Java3");
            Comment b = new Comment(6L, new Article(5L, "spring framework", "spring framework"), nickname, "sualmot");
            List<Comment> expected = Arrays.asList(a, b);
            // 검증
            assertEquals(expected.toString(), comments.toString(), "noop3의 모든 댓글을 출력!");
        }
    }
}