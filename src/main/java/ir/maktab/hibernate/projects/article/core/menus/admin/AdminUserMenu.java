package ir.maktab.hibernate.projects.article.core.menus.admin;

import ir.maktab.hibernate.projects.article.core.Actions;
import ir.maktab.hibernate.projects.article.core.AllRoles;
import ir.maktab.hibernate.projects.article.core.functions.Users;
import ir.maktab.hibernate.projects.article.core.menus.Menu;
import ir.maktab.hibernate.projects.article.entities.User;
import ir.maktab.hibernate.projects.article.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AdminUserMenu extends Menu {
    @Override
    public void execute() {
        command = "";
        while (!command.equals(Actions.back.name())) {

            UserRepository userRepository = UserRepository.getInstance();

            List<User> users
                    = userRepository.findAll().stream()
                    .filter(user -> user.getRoles().contains(AllRoles.admin.name()))
                    .collect(Collectors.toList());

            if (!users.isEmpty())
                Users.displayAll(users);
            else {
                System.out.println("\t\u26A0 Users List is empty!");
                break;
            }

            takeCommand();

            if (command.equals(Actions.exit.name())) {
                System.out.println("\n bye bye!");
                System.exit(0);
            }

            User chosenUser;

            if (command.equals(Actions.choose.name())) {
                chosenUser = Users.choose(users);
                new AdminChooseUserMenu(chosenUser).execute();
            }

            else if (command.equals(Actions.addrole.name())) {
                chosenUser = Users.choose(users);
                Users.addRole(chosenUser);
            }

            else if (command.equals(Actions.deleterole.name())) {
                chosenUser = Users.choose(users);
                Users.deleteRole(chosenUser);
            }

            else if (command.equals(Actions.delete.name())) {
                chosenUser = Users.choose(users);
                Users.delete(chosenUser);
            }

        }
    }

    @Override
    protected void displayMenu() {
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|                    Admin User Menu                            |");
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|  choose          ---->    Choose and change Role or Delete.   |");
        System.out.println("\t|  addrole         ---->    Add a Role to User.                 |");
        System.out.println("\t|  deleterole      ---->    Delete a Role from User.            |");
        System.out.println("\t|  delete          ---->    Delete User.                        |");
        System.out.println("\t|  back            ---->    Back to Admin Menu.                 |");
        System.out.println("\t|  exit            ---->    Exit.                               |");
        System.out.println("\t+---------------------------------------------------------------+");
    }

    @Override
    protected void setActions() {
        actions = new ArrayList<>(
                Arrays.asList(
                        Actions.choose.name()
                        , Actions.addrole.name()
                        , Actions.deleterole.name()
                        , Actions.delete.name()
                        , Actions.back.name()
                        , Actions.exit.name()));
    }
}
