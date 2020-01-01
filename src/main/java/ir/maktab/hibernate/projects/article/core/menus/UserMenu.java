package ir.maktab.hibernate.projects.article.core.menus;

import ir.maktab.hibernate.projects.article.core.Actions;
import ir.maktab.hibernate.projects.article.core.share.AuthenticationService;
import ir.maktab.hibernate.projects.article.features.usermanagement.impl.LogoutUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.usermanagement.usecases.LogoutUseCase;

import java.util.ArrayList;
import java.util.Arrays;

public class UserMenu extends Menu {
    @Override
    public void execute() {

        command = "";

        while (!command.equals(Actions.logout.name())) {
            takeCommand();

            if (command.equals(Actions.exit.name())) {
                System.out.println("\n bye bye!");
                System.exit(0);
            }

            else if (command.equals(Actions.articles.name())) {
                new ArticleMenu().execute();
            }

            else if (command.equals(Actions.myarticles.name())) {
                new UserArticleMenu().execute();
            }

            else if (command.equals(Actions.profile.name())) {
                new ProfileMenu().execute();
            }

            else if (command.equals(Actions.logout.name())) {
                LogoutUseCase logoutUseCase = new LogoutUseCaseImpl();
                logoutUseCase.logout();
                if (AuthenticationService.getInstance().getLoginUser() == null)
                    System.out.println("\t\u2714 Logout successful.\n");
                else
                    System.out.println("\t\u274c Logout failed!\n");
            }
        }
    }

    @Override
    protected void displayMenu() {
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|                      User Menu                                |");
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|  myarticles      ---->    Your Articles.                      |");
        System.out.println("\t|  articles        ---->    See all of Articles.                |");
        System.out.println("\t|  profile         ---->    See your Profile.                   |");
        System.out.println("\t|  logout          ---->    Logout.                             |");
        System.out.println("\t|  exit            ---->    Exit.                               |");
        System.out.println("\t+---------------------------------------------------------------+");
    }

    @Override
    protected void setActions() {
        actions = new ArrayList<>(
                Arrays.asList(
                        Actions.myarticles.name()
                        , Actions.articles.name()
                        , Actions.profile.name()
                        , Actions.logout.name()
                        , Actions.exit.name()));
    }
}
