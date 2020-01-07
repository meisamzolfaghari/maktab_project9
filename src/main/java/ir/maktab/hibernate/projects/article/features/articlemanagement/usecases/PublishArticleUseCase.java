package ir.maktab.hibernate.projects.article.features.articlemanagement.usecases;

import ir.maktab.hibernate.projects.article.entities.Article;
import ir.maktab.hibernate.projects.article.features.articlemanagement.ArticleUseCase;

import java.util.Date;

public interface PublishArticleUseCase extends ArticleUseCase {
    Article publish(Article articleForPublish, Date currentDate);
}
