package ir.maktab.hibernate.projects.article.core.menus;

import ir.maktab.hibernate.projects.article.core.Actions;
import ir.maktab.hibernate.projects.article.core.functions.Users;
import ir.maktab.hibernate.projects.article.core.share.AuthenticationService;
import ir.maktab.hibernate.projects.article.entities.User;

import java.util.ArrayList;
import java.util.Arrays;

public class ProfileMenu extends Menu {

    @Override
    public void execute() {

        command = "";

        while (!command.equals(Actions.back.name())) {
            User loginUser = AuthenticationService.getInstance().getLoginUser();

            Users.display(loginUser);

            takeCommand();

            if (command.equals(Actions.exit.name())) {
                System.out.println("\n bye bye!");
                System.exit(0);
            }

            else if (command.equals(Actions.edit.name())) {
                new EditProfileMenu().execute();
            }
        }
    }

    @Override
    protected void displayMenu() {
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|                     Profile Menu                              |");
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|  edit            ---->    Edit your Profile.                  |");
        System.out.println("\t|  back            ---->    Back to User Menu.                  |");
        System.out.println("\t|  exit            ---->    Exit.                               |");
        System.out.println("\t+---------------------------------------------------------------+");
    }

    @Override
    protected void setActions() {
        actions = new ArrayList<>(
                Arrays.asList(
                        Actions.edit.name()
                        , Actions.back.name()
                        , Actions.exit.name()));
    }
}
