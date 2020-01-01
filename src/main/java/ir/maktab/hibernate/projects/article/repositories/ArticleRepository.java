package ir.maktab.hibernate.projects.article.repositories;

import ir.maktab.hibernate.projects.article.core.config.hibernate.repository.CrudRepository;
import ir.maktab.hibernate.projects.article.entities.Article;

public class ArticleRepository extends CrudRepository<Article , Long> {

    private static ArticleRepository articleRepository;

    private ArticleRepository() {
    }

    public static ArticleRepository getInstance(){
        if (articleRepository == null)
            articleRepository = new ArticleRepository();
        return articleRepository;
    }

    @Override
    protected Class<Article> getEntityClass() {
        return Article.class;
    }
}
