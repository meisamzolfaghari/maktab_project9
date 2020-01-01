package ir.maktab.hibernate.projects.article.core.menus;

import ir.maktab.hibernate.projects.article.core.Actions;
import ir.maktab.hibernate.projects.article.core.functions.Users;
import ir.maktab.hibernate.projects.article.entities.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class EditProfileMenu extends Menu {

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);

        command = "";

        while (!command.equals(Actions.back.name())) {

            takeCommand();

            if (command.equals(Actions.exit.name())) {
                System.out.println("\n bye bye!");
                System.exit(0);
            }

            else if (command.equals(Actions.back.name())) continue;

            User editedLoginUser;

            if (command.equals(Actions.username.name())) {
                Users.ChangeUsername();
            } else if (command.equals(Actions.nationcode.name())) {
                Users.ChangeNationalCode();
            } else if (command.equals(Actions.birhday.name())) {
                Users.ChangeBirthDay();
            } else if (command.equals(Actions.password.name())) {
                Users.ChangePassword();
            }
        }
    }

    @Override
    protected void displayMenu() {
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|                       Edit Profile Menu                       |");
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|  username        ---->    Edit Username.                      |");
        System.out.println("\t|  nationcode      ---->    Edit National Code .                |");
        System.out.println("\t|  birthday        ---->    Edit Birthday.                      |");
        System.out.println("\t|  password        ---->    Edit Password.                      |");
        System.out.println("\t|  back            ---->    Back to Profile Menu.               |");
        System.out.println("\t|  exit            ---->    Exit.                               |");
        System.out.println("\t+---------------------------------------------------------------+");
    }

    @Override
    protected void setActions() {
        actions = new ArrayList<>(
                Arrays.asList(
                        Actions.username.name()
                        , Actions.nationcode.name()
                        , Actions.birhday.name()
                        , Actions.password.name()
                        , Actions.back.name()
                        , Actions.exit.name()));
    }
}
