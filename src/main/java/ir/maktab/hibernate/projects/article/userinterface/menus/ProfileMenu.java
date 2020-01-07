package ir.maktab.hibernate.projects.article.userinterface.menus;

import ir.maktab.hibernate.projects.article.core.Actions;
import ir.maktab.hibernate.projects.article.userinterface.functions.Users;

import java.util.ArrayList;
import java.util.Arrays;

public class ProfileMenu extends Menu {

    public ProfileMenu() {
        super();
    }

    @Override
    public void execute() {

        command = "";

        while (!command.equals(Actions.back.name())) {

            Users.display(loginUser);

            displayMenu();
            command = Users.takeCommand(actions);

            if (command.equals(Actions.exit.name())) {
                exit();
            }

            else if (command.equals(Actions.edit.name())) {
                Menu menu = new EditProfileMenu();
                menu.execute();
            }
        }
    }

    @Override
    protected void displayMenu() {
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|                     Profile Menu                              |");
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|  edit            ---->    Edit your Profile.                  |");
        System.out.println("\t|  back            ---->    Back to Last Menu.                  |");
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
