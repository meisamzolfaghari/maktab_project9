package ir.maktab.hibernate.projects.article.features.articlemanagement.usecases;

import ir.maktab.hibernate.projects.article.entities.Article;
import ir.maktab.hibernate.projects.article.entities.Category;
import ir.maktab.hibernate.projects.article.entities.Tag;
import ir.maktab.hibernate.projects.article.entities.User;
import ir.maktab.hibernate.projects.article.features.articlemanagement.ArticleUseCase;

import java.util.Date;
import java.util.List;

public interface AddArticleUseCase extends ArticleUseCase {
    Article add(String title , String brief , String Content , Date currentDate , User user, Category category, List<Tag> tags);
}
