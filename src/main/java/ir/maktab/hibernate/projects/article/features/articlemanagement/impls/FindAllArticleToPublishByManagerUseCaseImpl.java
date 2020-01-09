package ir.maktab.hibernate.projects.article.features.articlemanagement.impls;

import ir.maktab.hibernate.projects.article.core.AllRoles;
import ir.maktab.hibernate.projects.article.core.share.AuthenticationService;
import ir.maktab.hibernate.projects.article.entities.Article;
import ir.maktab.hibernate.projects.article.entities.User;
import ir.maktab.hibernate.projects.article.features.articlemanagement.usecases.FindAllArticleToPublishByManagerUseCase;
import ir.maktab.hibernate.projects.article.features.articlemanagement.usecases.FindAllPublishedArticleUseCase;
import ir.maktab.hibernate.projects.article.features.rolemanagement.impls.FindRoleByTitleUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.rolemanagement.usecases.FindRoleByTitleUseCase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FindAllArticleToPublishByManagerUseCaseImpl implements FindAllArticleToPublishByManagerUseCase {
    @Override
    public List<Article> list() {
        User loginUser = AuthenticationService.getInstance().getLoginUser();
        FindRoleByTitleUseCase findRoleByTitleUseCase = new FindRoleByTitleUseCaseImpl();
        if (loginUser == null || !loginUser.getRoles()
                .contains(findRoleByTitleUseCase.find(AllRoles.manager.name()))) {
            System.out.println("\t\u274c Failed to Find Articles! Access is For Manager.\n");
            return new ArrayList<>();
        }

        return articleRepository.findAll()
                .stream()
                .filter(article -> !article.isPublished()
                        && !article.getUser().getRoles()
                        .contains(findRoleByTitleUseCase.find(AllRoles.manager.name()))
                        && !article.getUser().getRoles()
                        .contains(findRoleByTitleUseCase.find(AllRoles.admin.name())))
                .collect(Collectors.toList());
    }
}
