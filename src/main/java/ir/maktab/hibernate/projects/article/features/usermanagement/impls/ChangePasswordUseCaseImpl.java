package ir.maktab.hibernate.projects.article.features.usermanagement.impls;

import ir.maktab.hibernate.projects.article.core.share.AuthenticationService;
import ir.maktab.hibernate.projects.article.entities.User;
import ir.maktab.hibernate.projects.article.features.usermanagement.usecases.ChangePasswordUseCase;

public class ChangePasswordUseCaseImpl implements ChangePasswordUseCase {
    @Override
    public User change(String newPassword) {
        if (newPassword == null || newPassword.isEmpty()) {
            System.out.println("\t\u274c Failed to Change Password! New Password Error.\n");
            return null;
        }
        User loginUser = AuthenticationService.getInstance().getLoginUser();
        if (loginUser == null) {
            System.out.println("\t\u274c Failed to Change Password! No User Logged In Error.\n");
            return null;
        }

        loginUser.setPassword(newPassword);

        userRepository.update(loginUser);
        User editedLoginUser = userRepository.findById(loginUser.getId());

        if (editedLoginUser.getPassword().equals(newPassword)) {
            AuthenticationService.getInstance().setLoginUser(editedLoginUser);
            System.out.println("\t\u2714 Password successfully Changed.\n");
        } else
            System.out.println("\t\u274c Failed to Change Password!\n");
        return editedLoginUser;
    }
}
