package ir.maktab.hibernate.projects.article.features.usermanagement.impls;

import ir.maktab.hibernate.projects.article.core.share.AuthenticationService;
import ir.maktab.hibernate.projects.article.entities.User;
import ir.maktab.hibernate.projects.article.features.usermanagement.usecases.ChangeNationalCodeUseCase;
import ir.maktab.hibernate.projects.article.features.usermanagement.usecases.ChangePasswordUseCase;

public class ChangeNationalCodeUseCaseImpl implements ChangeNationalCodeUseCase {
    @Override
    public User change(String newNationalCode) {
        if (newNationalCode == null || newNationalCode.isEmpty()) {
            System.out.println("\t\u274c Failed to Edit National Code! New National Code Error.\n");
            return null;
        }
        User loginUser = AuthenticationService.getInstance().getLoginUser();
        if (loginUser == null) {
            System.out.println("\t\u274c Failed to Edit National Code! No User Logged In Error.\n");
            return null;
        }

        loginUser.setNationalCode(newNationalCode);

        userRepository.update(loginUser);
        User editedLoginUser = userRepository.findById(loginUser.getId());

        if (editedLoginUser.getNationalCode().equals(newNationalCode)) {
            AuthenticationService.getInstance().setLoginUser(editedLoginUser);
            System.out.println("\t\u2714 National Code successfully Edited.\n");
        } else
            System.out.println("\t\u274c Failed to Edit National Code!\n");
        return editedLoginUser;
    }
}
