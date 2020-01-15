package ir.maktab.hibernate.projects.article.features.usermanagement.impls;

import ir.maktab.hibernate.projects.article.core.AllRoles;
import ir.maktab.hibernate.projects.article.core.share.AuthenticationService;
import ir.maktab.hibernate.projects.article.entities.Role;
import ir.maktab.hibernate.projects.article.entities.User;
import ir.maktab.hibernate.projects.article.features.usermanagement.usecases.PromoteUserByAdminUseCase;
import ir.maktab.hibernate.projects.article.userinterface.functions.Roles;
import ir.maktab.hibernate.projects.article.userinterface.functions.Users;

import java.util.List;

public class PromoteUserByAdminUseCaseImpl implements PromoteUserByAdminUseCase {
    @Override
    public Boolean promote(User user) {
        if (user == null || user.getRoles().isEmpty()) {
            System.out.println("\t\u274c Failed to Promote User! User Error.\n");
            return false;
        }
        List<Role> userRoles = user.getRoles();
        if (userRoles.isEmpty()) {
            System.out.println("\t\u274c Failed to Promote User! User has No Roles Error.\n");
            return false;
        }

        //check not to promote admin
        if (Users.isAdmin(user)) {
            System.out.println("\t\u274c Failed to Promote User! Admin Can't be Promoted.\n");
            return false;
        }

        //check admin is logged in
        User loginUser = AuthenticationService.getInstance().getLoginUser();
        if (loginUser == null || !Users.isAdmin(loginUser)) {
            System.out.println("\t\u274c Failed to Promote User! You Are not Admin Error.\n");
            return false;
        }

        //manager user can't be promoted
        if (userRoles.size() == 2 && Users.isManager(user)) {
            System.out.println("\t\u274c Promote Failed! This User is a " + AllRoles.manager.name() +
                    "and can't be Promoted More.\n");
            return false;
        }

        userRoles.add(Roles.getManagerRole());
        user.setRoles(userRoles);
        userRepository.update(user);
        if (Users.isManager(userRepository.findById(user.getId()))) {
            System.out.println("\t\u2714 User successfully Promoted to Manager.\n");
            return true;
        }
        else
            System.out.println("\t\u274c Failed to Promote To Manager!\n");
        return false;
    }
}
