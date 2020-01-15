package ir.maktab.hibernate.projects.article.features.usermanagement.impls;

import ir.maktab.hibernate.projects.article.core.AllRoles;
import ir.maktab.hibernate.projects.article.core.share.AuthenticationService;
import ir.maktab.hibernate.projects.article.entities.Role;
import ir.maktab.hibernate.projects.article.entities.User;
import ir.maktab.hibernate.projects.article.features.usermanagement.usecases.DemoteUserByAdminUseCase;
import ir.maktab.hibernate.projects.article.userinterface.functions.Roles;
import ir.maktab.hibernate.projects.article.userinterface.functions.Users;

import java.util.List;

public class DemoteUserByAdminUseCaseImpl implements DemoteUserByAdminUseCase {
    @Override
    public Boolean demote(User user) {
        if (user == null || user.getRoles().isEmpty()) {
            System.out.println("\t\u274c Failed to Demote User! User Error.\n");
            return false;
        }
        List<Role> userRoles = user.getRoles();
        if (userRoles.isEmpty()) {
            System.out.println("\t\u274c Failed to Demote User! User has No Roles Error.\n");
            return false;
        }

        //check not to demote admin
        if (Users.isAdmin(user)) {
            System.out.println("\t\u274c Failed to Demote User! Admin can't be Demoted.\n");
            return false;
        }

        //check admin is logged in
        User loginUser = AuthenticationService.getInstance().getLoginUser();
        if (loginUser == null || !Users.isAdmin(loginUser)) {
            System.out.println("\t\u274c Failed to Demote User! You Are not Admin.\n");
            return false;
        }

        //writer only user can't be demoted
        if (userRoles.size() == 1
                && Users.isWriter(user)) {
            System.out.println("\t\u274c Demote Failed! This User is just a " + AllRoles.writer.name() +
                    " and can't be Demoted More.\n");
            return false;
        }

        userRoles.remove(Roles.getManagerRole());
        user.setRoles(userRoles);
        userRepository.update(user);
        if (!Users.isManager(userRepository.findById(user.getId()))) {
            System.out.println("\t\u2714 User Successfully Demoted to Writer.\n");
            return true;
        }
        else
            System.out.println("\t\u274c Failed to Demote User!\n");
        return false;
    }
}
