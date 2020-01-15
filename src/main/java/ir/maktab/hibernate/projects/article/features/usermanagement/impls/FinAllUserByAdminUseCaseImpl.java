package ir.maktab.hibernate.projects.article.features.usermanagement.impls;

import ir.maktab.hibernate.projects.article.core.share.AuthenticationService;
import ir.maktab.hibernate.projects.article.entities.User;
import ir.maktab.hibernate.projects.article.features.usermanagement.usecases.FindAllUserByAdminUseCase;
import ir.maktab.hibernate.projects.article.userinterface.functions.Users;

import java.util.ArrayList;
import java.util.List;

public class FinAllUserByAdminUseCaseImpl implements FindAllUserByAdminUseCase {
    @Override
    public List<User> list() {

        User loginUser = AuthenticationService.getInstance().getLoginUser();
        if (loginUser == null || !Users.isAdmin(loginUser)) {
            System.out.println("\t\u274c Failed to Find User! There is no User Error.\n");
            return new ArrayList<>();
        }

        return userRepository.findAll();
    }
}
