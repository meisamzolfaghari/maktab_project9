package ir.maktab.hibernate.projects.article.features.articlemanagement.usecases;

import ir.maktab.hibernate.projects.article.entities.Article;
import ir.maktab.hibernate.projects.article.features.articlemanagement.ArticleUseCase;

import java.util.Date;

public interface UnPublishArticleUseCase extends ArticleUseCase {
    Article unPublish(Article articleForPublish, Date currentDate);
}
