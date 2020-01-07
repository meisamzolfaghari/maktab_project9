package ir.maktab.hibernate.projects.article.features.usermanagement.impls;

import ir.maktab.hibernate.projects.article.core.AllRoles;
import ir.maktab.hibernate.projects.article.core.share.AuthenticationService;
import ir.maktab.hibernate.projects.article.entities.User;
import ir.maktab.hibernate.projects.article.features.rolemanagement.impls.FindRoleByTitleUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.usermanagement.usecases.FindAllUserByAdminUseCase;

import java.util.ArrayList;
import java.util.List;

public class FinAllUserByAdminUseCaseImpl implements FindAllUserByAdminUseCase {
    @Override
    public List<User> list() {

        User loginUser = AuthenticationService.getInstance().getLoginUser();
        if (loginUser == null ||
                !loginUser.getRoles().contains(new FindRoleByTitleUseCaseImpl().find(AllRoles.admin.name())))
            return new ArrayList<>();

        return userRepository.findAll();
    }
}
