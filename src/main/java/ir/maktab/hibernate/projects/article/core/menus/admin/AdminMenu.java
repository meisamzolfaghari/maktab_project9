package ir.maktab.hibernate.projects.article.core.menus.admin;

import ir.maktab.hibernate.projects.article.core.Actions;
import ir.maktab.hibernate.projects.article.core.menus.FinalMenu;
import ir.maktab.hibernate.projects.article.core.menus.Menu;
import ir.maktab.hibernate.projects.article.core.menus.ProfileMenu;

import java.util.ArrayList;
import java.util.Arrays;

public class AdminMenu extends Menu {
    @Override
    public void execute() {

        command = "";
        while (!command.equals(Actions.exit.name())) {

            takeCommand();

            if (command.equals(Actions.exit.name())) {
                System.out.println("\n bye bye!");
                System.exit(0);
            }

            else if (command.equals(Actions.articles.name())) {
                new AdminArticleMenu().execute();
            }

            else if (command.equals(Actions.users.name())) {
                new AdminUserMenu().execute();
            }

            else if (command.equals(Actions.tags.name())) {
                new TagMenu().execute();
            }
            else if (command.equals(Actions.categories.name())) {
                new CategoryMenu().execute();
            }

            else if (command.equals(Actions.profile.name())) {
                new ProfileMenu().execute();
            }



        }
    }

    @Override
    protected void displayMenu() {
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|                    Admin Menu                                 |");
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|  articles        ---->    See and Edit all Articles.          |");
        System.out.println("\t|  users           ---->    See and Edit all Users.             |");
        System.out.println("\t|  tags            ---->    Add or Delete Tags.                 |");
        System.out.println("\t|  categories      ---->    Add or Delete Categories.           |");
        System.out.println("\t|  profile         ---->    See your Profile.                   |");
        System.out.println("\t|  logout          ---->    Logout.                             |");
        System.out.println("\t|  exit            ---->    Exit.                               |");
        System.out.println("\t+---------------------------------------------------------------+");
    }

    @Override
    protected void setActions() {
        actions = new ArrayList<>(
                Arrays.asList(
                        Actions.articles.name()
                        , Actions.users.name()
                        , Actions.tags.name()
                        , Actions.categories.name()
                        , Actions.profile.name()
                        , Actions.logout.name()
                        , Actions.exit.name()));
    }
}
