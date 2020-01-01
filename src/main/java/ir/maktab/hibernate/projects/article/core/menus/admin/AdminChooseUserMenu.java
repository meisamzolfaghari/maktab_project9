package ir.maktab.hibernate.projects.article.core.menus.admin;

import ir.maktab.hibernate.projects.article.core.Actions;
import ir.maktab.hibernate.projects.article.core.functions.Users;
import ir.maktab.hibernate.projects.article.core.menus.Menu;
import ir.maktab.hibernate.projects.article.entities.User;

import java.util.ArrayList;
import java.util.Arrays;

public class AdminChooseUserMenu extends Menu {

    private User chosenUser;

    public AdminChooseUserMenu(User chosenUser) {
        this.chosenUser = chosenUser;
    }

    @Override
    public void execute() {
        command = "";
        while (!command.equals(Actions.back.name())) {

            Users.display(chosenUser);

            takeCommand();

            if (command.equals(Actions.exit.name())) {
                System.out.println("\n bye bye!");
                System.exit(0);
            }

            if (command.equals(Actions.addrole.name())) {
                Users.addRole(chosenUser);
            }

            else if (command.equals(Actions.deleterole.name())) {
                Users.deleteRole(chosenUser);
            }

            else if (command.equals(Actions.delete.name())) {
                Users.delete(chosenUser);
            }

        }
    }

    @Override
    protected void displayMenu() {
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|                    Admin Choose User Menu                     |");
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|  addrole         ---->    Add a Role to Chosen User.          |");
        System.out.println("\t|  deleterole      ---->    Delete a Role from Chosen User.     |");
        System.out.println("\t|  delete          ---->    Delete Chosen User.                 |");
        System.out.println("\t|  back            ---->    Back to Admin User Menu.            |");
        System.out.println("\t|  exit            ---->    Exit.                               |");
        System.out.println("\t+---------------------------------------------------------------+");
    }

    @Override
    protected void setActions() {
        actions = new ArrayList<>(
                Arrays.asList(
                        Actions.addrole.name()
                        , Actions.deleterole.name()
                        , Actions.delete.name()
                        , Actions.back.name()
                        , Actions.exit.name()));
    }
}
