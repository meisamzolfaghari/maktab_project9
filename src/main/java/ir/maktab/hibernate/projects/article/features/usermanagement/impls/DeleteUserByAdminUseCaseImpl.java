package ir.maktab.hibernate.projects.article.features.usermanagement.impls;

import ir.maktab.hibernate.projects.article.entities.User;
import ir.maktab.hibernate.projects.article.features.usermanagement.usecases.DeleteUserByAdminUseCase;
import ir.maktab.hibernate.projects.article.repositories.ArticleRepository;
import ir.maktab.hibernate.projects.article.userinterface.functions.Users;

public class DeleteUserByAdminUseCaseImpl implements DeleteUserByAdminUseCase {
    @Override
    public Boolean delete(User user) {
        if (userRepository.findById(user.getId()) == null) {
            System.out.println("\t\u274c Delete User Failed! User Error.\n");
            return false;
        }
        if (Users.isAdmin(user)) {
            System.out.println("\t\u274c Delete Admin Failed!\n");
            return false;
        }
        ArticleRepository articleRepository = ArticleRepository.getInstance();
        articleRepository.findAll().stream()
                .filter(article -> article.getUser().equals(user))
                .forEach(articleRepository::remove);

        userRepository.remove(user);
        if (userRepository.findById(user.getId()) == null) {
            System.out.println("\t\u2714 User successfully Deleted.\n");
            return true;
        }
        else
            System.out.println("\t\u274c Delete User Failed!\n");
        return false;
    }
}
