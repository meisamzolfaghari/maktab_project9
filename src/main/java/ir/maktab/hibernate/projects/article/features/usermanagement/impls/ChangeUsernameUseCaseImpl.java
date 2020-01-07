package ir.maktab.hibernate.projects.article.features.usermanagement.impls;

import ir.maktab.hibernate.projects.article.core.share.AuthenticationService;
import ir.maktab.hibernate.projects.article.entities.User;
import ir.maktab.hibernate.projects.article.features.usermanagement.usecases.ChangeUsernameUseCase;
import ir.maktab.hibernate.projects.article.features.usermanagement.usecases.IsUniqueUsernameUseCase;

public class ChangeUsernameUseCaseImpl implements ChangeUsernameUseCase {
    @Override
    public User change(String newUsername) {
        if (newUsername == null || newUsername.isEmpty()) return null;
        User loginUser = AuthenticationService.getInstance().getLoginUser();
        if (loginUser == null) return null;

        IsUniqueUsernameUseCase isUniqueUsernameUseCase
                = new IsUniqueUsernameUseCaseImpl();

        if (!isUniqueUsernameUseCase.test(newUsername)) {
            System.out.println("\t\u26a0 Sorry! username is Owned by someone else! try another username...");
            return null;
        }

        loginUser.setUsername(newUsername);
        userRepository.update(loginUser);
        User editedLoginUser = userRepository.findById(loginUser.getId());

        if (editedLoginUser.getUsername().equals(newUsername)) {
            AuthenticationService.getInstance().setLoginUser(editedLoginUser);
            System.out.println("\t\u2714 Username successfully Edited.\n");
        } else
            System.out.println("\t\u274c Failed to Edit Username!\n");

        return editedLoginUser;
    }
}
