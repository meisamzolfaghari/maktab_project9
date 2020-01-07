package ir.maktab.hibernate.projects.article.features.articlemanagement.usecases;

import ir.maktab.hibernate.projects.article.entities.Article;
import ir.maktab.hibernate.projects.article.features.articlemanagement.ArticleUseCase;

import java.util.List;

public interface FindAllArticleToPublishByManagerUseCase extends ArticleUseCase {
    List<Article> list();
}
