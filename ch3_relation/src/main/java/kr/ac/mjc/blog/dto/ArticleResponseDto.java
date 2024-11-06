package kr.ac.mjc.blog.dto;

import kr.ac.mjc.blog.domain.Article;

import java.util.List;

public class ArticleResponseDto {
    private boolean isSuccess;
    private Article article;

    private List<Article> list;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public List<Article> getList() {
        return list;
    }

    public void setList(List<Article> list) {
        this.list = list;
    }
}
