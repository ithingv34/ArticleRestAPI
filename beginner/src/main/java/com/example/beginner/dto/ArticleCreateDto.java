package com.example.beginner.dto;


import com.example.beginner.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleCreateDto {

    private String title;
    private String content;


    public Article toEntity() {
        return new Article(null, title, content);
    }
}
