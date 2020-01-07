package ir.maktab.hibernate.projects.article.features.articlemanagement.usecases;

import ir.maktab.hibernate.projects.article.entities.Article;
import ir.maktab.hibernate.projects.article.features.articlemanagement.ArticleUseCase;

import java.util.Date;

public interface ChangeArticleTitleUseCase extends ArticleUseCase {
    Article change(Article articleForEdit , String newTitle , Date currentDate);
}
