package ir.maktab.hibernate.projects.article.features.usermanagement.impls;

import ir.maktab.hibernate.projects.article.entities.User;
import ir.maktab.hibernate.projects.article.features.usermanagement.usecases.RegisterUseCase;
import ir.maktab.hibernate.projects.article.userinterface.functions.Roles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class RegisterUseCaseImpl implements RegisterUseCase {
    @Override
    public User register(String username, String nationalCode, Date birthday) {
        if (username == null || username.isEmpty()){
            System.out.println("\t\u274c Register Failed! Username Error.\n");
            return null;
        }
        if (nationalCode == null || nationalCode.isEmpty()){
            System.out.println("\t\u274c Register Failed! National Code Error.\n");
            return null;
        }
        if (birthday == null) {
            System.out.println("\t\u274c Register Failed! Birthday Error.\n");
            return null;
        }

        if (Roles.getWriterRole() == null) {
            System.out.println("\t\u274c Failed to Promote User! there is not Writer Role in Database Error.\n");
            return null;
        }

        User userToAdd = new User(null, username, nationalCode, birthday
                , nationalCode, new ArrayList<>(), new ArrayList<>(Collections.singletonList(Roles.getWriterRole())));

        userToAdd.setId(userRepository.save(userToAdd));
        User registeredUser = userRepository.findById(userToAdd.getId());
        if (registeredUser != null)
            System.out.println("\t\u2714 User successfully Registered.\n");
        else
            System.out.println("\t\u274c Register Failed!\n");

        return registeredUser;
    }
}
