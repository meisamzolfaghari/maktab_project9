package ir.maktab.hibernate.projects.article.features.usermanagement.impls;

import ir.maktab.hibernate.projects.article.core.share.AuthenticationService;
import ir.maktab.hibernate.projects.article.features.usermanagement.usecases.LogoutUseCase;

public class LogoutUseCaseImpl implements LogoutUseCase {
    @Override
    public void logout() {
        AuthenticationService.getInstance().setLoginUser(null);
        if (AuthenticationService.getInstance().getLoginUser() == null)
            System.out.println("\t\u2714 Logout Successful.\n");
    }
}
