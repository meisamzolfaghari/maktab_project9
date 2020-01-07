package ir.maktab.hibernate.projects.article.core.config.admin;

import ir.maktab.hibernate.projects.article.core.AllRoles;
import ir.maktab.hibernate.projects.article.entities.User;
import ir.maktab.hibernate.projects.article.features.rolemanagement.impls.FindRoleByTitleUseCaseImpl;
import ir.maktab.hibernate.projects.article.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AdminConfig {
    public static void config() {
        UserRepository userRepository = UserRepository.getInstance();
        List<User> users = userRepository.findAll();
        if (users.isEmpty() || !users.stream().anyMatch(user -> user.getRoles()
                .contains(new FindRoleByTitleUseCaseImpl().find(AllRoles.admin.name()))))
            userRepository.save(new User(null, AllRoles.admin.name(), "123"
                    , new Date(System.currentTimeMillis()), "123", new ArrayList<>()
                    , new ArrayList<>(Arrays.asList(new FindRoleByTitleUseCaseImpl().find(AllRoles.admin.name())))));
    }
}
