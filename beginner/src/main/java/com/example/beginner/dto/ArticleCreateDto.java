package com.example.beginner.dto;


import com.example.beginner.entity.Article;

public class ArticleCreateDto {

    private String title;
    private String content;

    public ArticleCreateDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    

    @Override
    public String toString() {
        return "ArticleCreateDto{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Article toEntity() {
        return new Article(null, title, content);
    }
}
