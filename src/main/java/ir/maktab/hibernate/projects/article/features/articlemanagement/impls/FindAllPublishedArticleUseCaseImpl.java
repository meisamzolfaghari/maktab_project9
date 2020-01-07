package ir.maktab.hibernate.projects.article.features.articlemanagement.impls;

import ir.maktab.hibernate.projects.article.entities.Article;
import ir.maktab.hibernate.projects.article.features.articlemanagement.usecases.FindAllPublishedArticleUseCase;

import java.util.List;
import java.util.stream.Collectors;

public class FindAllPublishedArticleUseCaseImpl implements FindAllPublishedArticleUseCase {
    @Override
    public List<Article> list() {
        return articleRepository.findAll()
                .stream()
                .filter(Article::isPublished)
                .collect(Collectors.toList());
    }
}
