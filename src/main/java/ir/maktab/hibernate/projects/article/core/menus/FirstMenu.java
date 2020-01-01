package ir.maktab.hibernate.projects.article.core.menus;

import ir.maktab.hibernate.projects.article.core.Actions;
import ir.maktab.hibernate.projects.article.core.functions.Users;
import ir.maktab.hibernate.projects.article.core.share.AuthenticationService;
import ir.maktab.hibernate.projects.article.entities.User;

import java.util.ArrayList;
import java.util.Arrays;

public class FirstMenu extends Menu {

    @Override
    public void execute() {

        while (true) {

            takeCommand();

            if (command.equals(Actions.exit.name())) {
                System.out.println("\n bye bye!");
                System.exit(0);
            }

            else if (command.equals(Actions.articles.name())) {
                new ArticleMenu().execute();
            }

            else if (command.equals(Actions.login.name())) {

                Users.login();

                User loginUser = AuthenticationService.getInstance().getLoginUser();

                if (loginUser != null) {
                    System.out.println("\t\u2714 Login successful.\n");

                    new UserMenu().execute();
                }

                else
                    System.out.println("\t\u274c Login failed!\n");
            }

            else if (command.equals(Actions.register.name())) {
                Users.add();
            }
        }
    }

    @Override
    protected void displayMenu() {
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|                      First Menu                               |");
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|  articles        ---->    See all of Articles.                |");
        System.out.println("\t|  login           ---->    Login.                              |");
        System.out.println("\t|  register        ---->    Register.                           |");
        System.out.println("\t|  exit            ---->    Exit.                               |");
        System.out.println("\t+---------------------------------------------------------------+");
    }

    @Override
    protected void setActions() {
        actions = new ArrayList<>(
                Arrays.asList(
                        Actions.articles.name()
                        , Actions.login.name()
                        , Actions.register.name()
                        , Actions.exit.name()));
    }
}
