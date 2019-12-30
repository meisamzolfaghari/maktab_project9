package ir.maktab.java32.projects.articleapp.repositories;

import ir.maktab.java32.projects.articleapp.core.config.hibernate.repository.CrudRepository;
import ir.maktab.java32.projects.articleapp.entities.Article;

public class ArticleRepository extends CrudRepository<Article , Long> {

    private static ArticleRepository articleRepository;

    private ArticleRepository() {
    }

    public ArticleRepository getInstance(){
        if (articleRepository == null)
            articleRepository = new ArticleRepository();
        return articleRepository;
    }

    @Override
    protected Class<Article> getEntityClass() {
        return Article.class;
    }
}
