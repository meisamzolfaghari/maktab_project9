package ir.maktab.hibernate.projects.article.features.usermanagement.impls;

import ir.maktab.hibernate.projects.article.core.AllRoles;
import ir.maktab.hibernate.projects.article.core.share.AuthenticationService;
import ir.maktab.hibernate.projects.article.entities.Role;
import ir.maktab.hibernate.projects.article.entities.User;
import ir.maktab.hibernate.projects.article.features.rolemanagement.impls.FindAllRoleUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.rolemanagement.impls.FindRoleByTitleUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.rolemanagement.usecases.FindAllRoleUseCase;
import ir.maktab.hibernate.projects.article.features.rolemanagement.usecases.FindRoleByTitleUseCase;
import ir.maktab.hibernate.projects.article.features.usermanagement.usecases.PromoteUserByAdminUseCase;

import java.util.List;

public class PromoteUserByAdminUseCaseImpl implements PromoteUserByAdminUseCase {
    @Override
    public Boolean promote(User user) {
        if (user == null || user.getRoles().isEmpty()) return false;
        List<Role> userRoles = user.getRoles();
        if (userRoles.isEmpty()) return false;

        FindRoleByTitleUseCase findRoleByTitleUseCase = new FindRoleByTitleUseCaseImpl();

        //check not to promote admin
        if (userRoles.contains(findRoleByTitleUseCase.find(AllRoles.admin.name())))
            return false;

        //check admin is logged in
        User loginUser = AuthenticationService.getInstance().getLoginUser();
        if (loginUser == null ||
                !loginUser.getRoles().contains(findRoleByTitleUseCase.find(AllRoles.admin.name())))
            return false;

        //manager user can't be promoted
        if (userRoles.size() == 2
                && userRoles.contains(findRoleByTitleUseCase.find(AllRoles.manager.name()))) {
            System.out.println("\t\u274c Promote Failed! This User is a " + AllRoles.manager.name() +
                    "and can't be Promoted More.\n");
            return false;
        }

        userRoles.add(findRoleByTitleUseCase.find(AllRoles.manager.name()));
        user.setRoles(userRoles);
        userRepository.update(user);
        if (userRepository.findById(user.getId()).getRoles()
                .contains(findRoleByTitleUseCase.find(AllRoles.manager.name()))) {
            System.out.println("\t\u2714 User successfully Promoted to Manager.\n");
            return true;
        }
        else
            System.out.println("\t\u274c Failed to Promote To Manager!\n");
        return false;
    }
}
