package ir.maktab.hibernate.projects.article.features.usermanagement.impls;

import ir.maktab.hibernate.projects.article.core.AllRoles;
import ir.maktab.hibernate.projects.article.core.share.AuthenticationService;
import ir.maktab.hibernate.projects.article.entities.Role;
import ir.maktab.hibernate.projects.article.entities.User;
import ir.maktab.hibernate.projects.article.features.rolemanagement.impls.FindRoleByTitleUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.rolemanagement.usecases.FindRoleByTitleUseCase;
import ir.maktab.hibernate.projects.article.features.usermanagement.usecases.DemoteUserByAdminUseCase;

import java.util.List;

public class DemoteUserByAdminUseCaseImpl implements DemoteUserByAdminUseCase {
    @Override
    public Boolean demote(User user) {
        if (user == null || user.getRoles().isEmpty()) return false;
        List<Role> userRoles = user.getRoles();
        if (userRoles.isEmpty()) return false;

        FindRoleByTitleUseCase findRoleByTitleUseCase = new FindRoleByTitleUseCaseImpl();

        //check not to demote admin
        if (userRoles.contains(findRoleByTitleUseCase.find(AllRoles.admin.name())))
            return false;

        //check admin is logged in
        User loginUser = AuthenticationService.getInstance().getLoginUser();
        if (loginUser == null ||
                !loginUser.getRoles().contains(findRoleByTitleUseCase.find(AllRoles.admin.name())))
            return false;

        //writer only user can't be demoted
        if (userRoles.size() == 1
                && userRoles.contains(findRoleByTitleUseCase.find(AllRoles.writer.name()))) {
            System.out.println("\t\u274c Demote Failed! This User is just a " + AllRoles.writer.name() +
                    " and can't be Demoted More.\n");
            return false;
        }


        userRoles.remove(findRoleByTitleUseCase.find(AllRoles.manager.name()));
        user.setRoles(userRoles);
        userRepository.update(user);
        if (!userRepository.findById(user.getId()).getRoles()
                .contains(findRoleByTitleUseCase.find(AllRoles.manager.name()))) {
            System.out.println("\t\u2714 User Successfully Demoted to Writer.\n");
            return true;
        }
        else
            System.out.println("\t\u274c Failed to Demote User!\n");
        return false;
    }
}
