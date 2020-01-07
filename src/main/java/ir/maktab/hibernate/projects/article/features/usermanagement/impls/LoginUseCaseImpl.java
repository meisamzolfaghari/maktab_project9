package ir.maktab.hibernate.projects.article.features.usermanagement.impls;

import ir.maktab.hibernate.projects.article.core.share.AuthenticationService;
import ir.maktab.hibernate.projects.article.entities.User;
import ir.maktab.hibernate.projects.article.features.usermanagement.usecases.LoginUseCase;

public class LoginUseCaseImpl implements LoginUseCase {
    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty()) {
            System.out.println("\t\u274c Login failed!\n");
            return null;
        }
        if (password == null || password.isEmpty()) {
            System.out.println("\t\u274c Login failed!\n");
            return null;
        }

        for (User user : userRepository.findAll())
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                AuthenticationService.getInstance().setLoginUser(user);
                System.out.println("\t\u2714 Login successful.\n");
                return user;
            }

        System.out.println("\t\u274c Login failed!\n");
        return null;
    }
}
