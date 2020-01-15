package ir.maktab.hibernate.projects.article.features.articlemanagement.impls;

import ir.maktab.hibernate.projects.article.core.share.AuthenticationService;
import ir.maktab.hibernate.projects.article.entities.Article;
import ir.maktab.hibernate.projects.article.entities.User;
import ir.maktab.hibernate.projects.article.features.articlemanagement.usecases.FindAllArticleByAdminUseCase;
import ir.maktab.hibernate.projects.article.userinterface.functions.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FindAllArticleByAdminUseCaseImpl implements FindAllArticleByAdminUseCase {
    @Override
    public List<Article> list() {
        User loginUser = AuthenticationService.getInstance().getLoginUser();
        if (loginUser == null || !Users.isAdmin(loginUser)) {
            System.out.println("\t\u274c Failed to Find Articles! Access is just For Admin.\n");
            return new ArrayList<>();
        }

        return articleRepository.findAll()
                .stream()
                .filter(article -> !Users.isAdmin(article.getUser()))
                .collect(Collectors.toList());
    }
}
