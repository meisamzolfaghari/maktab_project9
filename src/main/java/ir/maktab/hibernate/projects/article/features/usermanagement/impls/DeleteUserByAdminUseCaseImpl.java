package ir.maktab.hibernate.projects.article.features.usermanagement.impls;

import ir.maktab.hibernate.projects.article.entities.User;
import ir.maktab.hibernate.projects.article.features.usermanagement.usecases.DeleteUserByAdminUseCase;
import ir.maktab.hibernate.projects.article.repositories.ArticleRepository;

public class DeleteUserByAdminUseCaseImpl implements DeleteUserByAdminUseCase {
    @Override
    public Boolean delete(User user) {
        if (userRepository.findById(user.getId()) == null) return false;
        ArticleRepository articleRepository = ArticleRepository.getInstance();
        articleRepository.findAll().stream()
                .filter(article -> article.getUser().equals(user))
                .forEach(article -> articleRepository.remove(article));
        userRepository.remove(user);
        if (userRepository.findById(user.getId()) == null) {
            System.out.println("\t\u2714 Article successfully Deleted.\n");
            return true;
        }
        else
            System.out.println("\t\u274c Delete Article Failed!\n");
        return false;
    }
}
