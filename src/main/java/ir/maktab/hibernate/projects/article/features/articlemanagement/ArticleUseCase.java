package ir.maktab.hibernate.projects.article.features.articlemanagement;

import ir.maktab.hibernate.projects.article.repositories.ArticleRepository;

public interface ArticleUseCase {
    ArticleRepository articleRepository = ArticleRepository.getInstance();
}
