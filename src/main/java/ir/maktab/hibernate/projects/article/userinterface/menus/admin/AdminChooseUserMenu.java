package ir.maktab.hibernate.projects.article.userinterface.menus.admin;

import ir.maktab.hibernate.projects.article.core.Actions;
import ir.maktab.hibernate.projects.article.core.share.AuthenticationService;
import ir.maktab.hibernate.projects.article.userinterface.functions.Users;
import ir.maktab.hibernate.projects.article.userinterface.menus.Menu;
import ir.maktab.hibernate.projects.article.entities.User;
import ir.maktab.hibernate.projects.article.features.usermanagement.impls.DeleteUserByAdminUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.usermanagement.impls.DemoteUserByAdminUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.usermanagement.impls.PromoteUserByAdminUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.usermanagement.usecases.DeleteUserByAdminUseCase;
import ir.maktab.hibernate.projects.article.features.usermanagement.usecases.DemoteUserByAdminUseCase;
import ir.maktab.hibernate.projects.article.features.usermanagement.usecases.PromoteUserByAdminUseCase;

import java.util.ArrayList;
import java.util.Arrays;

public class AdminChooseUserMenu extends Menu {

    private User chosenUser;

    public AdminChooseUserMenu(User chosenUser) {
        this.chosenUser = chosenUser;
        setActions();
    }

    @Override
    public void execute() {
        command = "";
        while (!command.equals(Actions.back.name())) {

            Users.display(chosenUser);

            displayMenu();
            command = Users.takeCommand(actions);

            if (command.equals(Actions.exit.name())) {
                exit();
            }

            if (command.equals(Actions.promote.name())) {
                PromoteUserByAdminUseCase promoteUserByAdminUseCase
                        = new PromoteUserByAdminUseCaseImpl();
                promoteUserByAdminUseCase.promote(chosenUser);
            }

            else if (command.equals(Actions.demote.name())) {
                DemoteUserByAdminUseCase demoteUserByAdminUseCase
                        = new DemoteUserByAdminUseCaseImpl();
                demoteUserByAdminUseCase.demote(chosenUser);
            }

            else if (command.equals(Actions.delete.name())) {
                DeleteUserByAdminUseCase deleteUserByAdminUseCase
                        = new DeleteUserByAdminUseCaseImpl();
                deleteUserByAdminUseCase.delete(chosenUser);
                break;
            }

        }
    }

    @Override
    protected void displayMenu() {
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|                    Admin Choose User Menu                     |");
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|  promote         ---->    promote User.                       |");
        System.out.println("\t|  demote          ---->    Demote User.                        |");
        System.out.println("\t|  delete          ---->    Delete Chosen User.                 |");
        System.out.println("\t|  back            ---->    Back to Admin User Menu.            |");
        System.out.println("\t|  exit            ---->    Exit.                               |");
        System.out.println("\t+---------------------------------------------------------------+");
    }

    @Override
    protected void setActions() {
        actions = new ArrayList<>(
                Arrays.asList(
                        Actions.promote.name()
                        , Actions.demote.name()
                        , Actions.delete.name()
                        , Actions.back.name()
                        , Actions.exit.name()));
    }
}
