package ir.maktab.hibernate.projects.article.features.usermanagement.impls;

import ir.maktab.hibernate.projects.article.core.share.AuthenticationService;
import ir.maktab.hibernate.projects.article.entities.User;
import ir.maktab.hibernate.projects.article.features.usermanagement.usecases.ChangeBirthdayUseCase;
import ir.maktab.hibernate.projects.article.features.usermanagement.usecases.ChangePasswordUseCase;

import java.util.Date;

public class ChangeBirthdayUseCaseImpl implements ChangeBirthdayUseCase {
    @Override
    public User change(Date newBirthday) {
        if (newBirthday == null) return null;
        User loginUser = AuthenticationService.getInstance().getLoginUser();
        if (loginUser == null) return null;

        loginUser.setBirthDay(newBirthday);

        userRepository.update(loginUser);
        User editedLoginUser = userRepository.findById(loginUser.getId());

        if (editedLoginUser.getBirthDay().equals(newBirthday)) {
            AuthenticationService.getInstance().setLoginUser(editedLoginUser);
            System.out.println("\t\u2714 Birth Day successfully Edited.\n");
        } else
            System.out.println("\t\u274c Failed to Edit Birth Day!\n");

        return editedLoginUser;
    }
}
