package ir.maktab.hibernate.projects.article.userinterface.menus.admin;

import ir.maktab.hibernate.projects.article.core.Actions;
import ir.maktab.hibernate.projects.article.userinterface.functions.Users;
import ir.maktab.hibernate.projects.article.userinterface.menus.Menu;
import ir.maktab.hibernate.projects.article.entities.User;
import ir.maktab.hibernate.projects.article.features.usermanagement.impls.DeleteUserByAdminUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.usermanagement.impls.DemoteUserByAdminUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.usermanagement.impls.FinAllUserByAdminUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.usermanagement.impls.PromoteUserByAdminUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.usermanagement.usecases.DeleteUserByAdminUseCase;
import ir.maktab.hibernate.projects.article.features.usermanagement.usecases.DemoteUserByAdminUseCase;
import ir.maktab.hibernate.projects.article.features.usermanagement.usecases.FindAllUserByAdminUseCase;
import ir.maktab.hibernate.projects.article.features.usermanagement.usecases.PromoteUserByAdminUseCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminUserMenu extends Menu {

    public AdminUserMenu() {
        super();
    }

    @Override
    public void execute() {
        command = "";
        while (!command.equals(Actions.back.name())) {

            FindAllUserByAdminUseCase findAllUserByAdminUseCase
                    = new FinAllUserByAdminUseCaseImpl();

            List<User> users = findAllUserByAdminUseCase.list();

            if (!users.isEmpty())
                Users.displayAll(users);
            else {
                System.out.println("\t\u26A0 Users List is empty!");
                break;
            }

            displayMenu();
            command = Users.takeCommand(actions);

            if (command.equals(Actions.exit.name())) {
                exit();
            }

            User chosenUser;

            if (command.equals(Actions.choose.name())) {
                chosenUser = Users.choose(users);
                if (chosenUser != null) {
                    Menu menu = new AdminChooseUserMenu(chosenUser);
                    menu.execute();
                }
            } else if (command.equals(Actions.promote.name())) {
                chosenUser = Users.choose(users);
                if (chosenUser != null) {
                    PromoteUserByAdminUseCase promoteUserByAdminUseCase
                            = new PromoteUserByAdminUseCaseImpl();
                    promoteUserByAdminUseCase.promote(chosenUser);
                }
            } else if (command.equals(Actions.demote.name())) {
                chosenUser = Users.choose(users);
                if (chosenUser != null) {
                    DemoteUserByAdminUseCase demoteUserByAdminUseCase
                            = new DemoteUserByAdminUseCaseImpl();
                    demoteUserByAdminUseCase.demote(chosenUser);
                }
            } else if (command.equals(Actions.delete.name())) {
                chosenUser = Users.choose(users);
                if (chosenUser != null) {
                    DeleteUserByAdminUseCase deleteUserByAdminUseCase
                            = new DeleteUserByAdminUseCaseImpl();
                    deleteUserByAdminUseCase.delete(chosenUser);
                }
            }
        }
    }

    @Override
    protected void displayMenu() {
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|                    Admin User Menu                            |");
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|  choose          ---->    Choose User.                        |");
        System.out.println("\t|  promote         ---->    promote User.                       |");
        System.out.println("\t|  demote          ---->    Demote User.                        |");
        System.out.println("\t|  delete          ---->    Delete User.                        |");
        System.out.println("\t|  back            ---->    Back to Last Menu.                  |");
        System.out.println("\t|  exit            ---->    Exit.                               |");
        System.out.println("\t+---------------------------------------------------------------+");
    }

    @Override
    protected void setActions() {
        actions = new ArrayList<>(
                Arrays.asList(
                        Actions.choose.name()
                        , Actions.promote.name()
                        , Actions.demote.name()
                        , Actions.delete.name()
                        , Actions.back.name()
                        , Actions.exit.name()));
    }
}
