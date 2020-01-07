package ir.maktab.hibernate.projects.article.features.usermanagement.impls;

import ir.maktab.hibernate.projects.article.core.AllRoles;
import ir.maktab.hibernate.projects.article.entities.Role;
import ir.maktab.hibernate.projects.article.entities.User;
import ir.maktab.hibernate.projects.article.features.rolemanagement.impls.FindRoleByTitleUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.rolemanagement.usecases.FindRoleByTitleUseCase;
import ir.maktab.hibernate.projects.article.features.usermanagement.usecases.RegisterUseCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class RegisterUseCaseImpl implements RegisterUseCase {
    @Override
    public User register(String username, String nationalCode, Date birthday) {
        if (username == null || username.isEmpty()){
            System.out.println("\t\u274c Register Failed!\n");
            return null;
        }
        if (nationalCode == null || nationalCode.isEmpty()){
            System.out.println("\t\u274c Register Failed!\n");
            return null;
        }
        if (birthday == null) {
            System.out.println("\t\u274c Register Failed!\n");
            return null;
        }

        FindRoleByTitleUseCase findRoleByTitleUseCase
                = new FindRoleByTitleUseCaseImpl();
        Role role = findRoleByTitleUseCase.find(AllRoles.writer.name());
        if (role == null) return null;

        User userToAdd = new User(null, username, nationalCode, birthday
                , nationalCode, new ArrayList<>(), new ArrayList<>(Arrays.asList(role)));

        userToAdd.setId(userRepository.save(userToAdd));
        User registeredUser = userRepository.findById(userToAdd.getId());
        if (registeredUser != null)
            System.out.println("\t\u2714 User successfully Registered.\n");
        else
            System.out.println("\t\u274c Register Failed!\n");

        return registeredUser;
    }
}
