package ir.maktab.hibernate.projects.article.features.articlemanagement.impls;

import ir.maktab.hibernate.projects.article.core.share.AuthenticationService;
import ir.maktab.hibernate.projects.article.entities.Article;
import ir.maktab.hibernate.projects.article.entities.User;
import ir.maktab.hibernate.projects.article.features.articlemanagement.usecases.FindAllArticleToPublishByManagerUseCase;
import ir.maktab.hibernate.projects.article.userinterface.functions.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FindAllArticleToPublishByManagerUseCaseImpl implements FindAllArticleToPublishByManagerUseCase {
    @Override
    public List<Article> list() {
        User loginUser = AuthenticationService.getInstance().getLoginUser();
        if (loginUser == null || !Users.isManager(loginUser)) {
            System.out.println("\t\u274c Failed to Find Articles! Access is For Manager.\n");
            return new ArrayList<>();
        }

        return articleRepository.findAll()
                .stream()
                .filter(article -> !article.isPublished()
                        && !Users.isManager(article.getUser())
                        && !Users.isAdmin(article.getUser()))
                .collect(Collectors.toList());
    }
}
