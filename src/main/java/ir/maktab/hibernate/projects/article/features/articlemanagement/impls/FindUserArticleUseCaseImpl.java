package ir.maktab.hibernate.projects.article.features.articlemanagement.impls;

import ir.maktab.hibernate.projects.article.entities.Article;
import ir.maktab.hibernate.projects.article.entities.User;
import ir.maktab.hibernate.projects.article.features.articlemanagement.usecases.FindUserArticleUseCase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FindUserArticleUseCaseImpl implements FindUserArticleUseCase {
    @Override
    public List<Article> findAll(User user) {
        if (user == null) return new ArrayList<>();

        return articleRepository.findAll()
                .stream()
                .filter(article -> article.getUser().equals(user))
                .collect(Collectors.toList());
    }
}
