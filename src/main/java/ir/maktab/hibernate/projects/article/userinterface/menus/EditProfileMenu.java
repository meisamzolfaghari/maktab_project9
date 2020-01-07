package ir.maktab.hibernate.projects.article.userinterface.menus;

import ir.maktab.hibernate.projects.article.core.Actions;
import ir.maktab.hibernate.projects.article.userinterface.functions.Users;
import ir.maktab.hibernate.projects.article.features.usermanagement.impls.ChangeBirthdayUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.usermanagement.impls.ChangeNationalCodeUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.usermanagement.impls.ChangePasswordUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.usermanagement.impls.ChangeUsernameUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.usermanagement.usecases.ChangeBirthdayUseCase;
import ir.maktab.hibernate.projects.article.features.usermanagement.usecases.ChangeNationalCodeUseCase;
import ir.maktab.hibernate.projects.article.features.usermanagement.usecases.ChangePasswordUseCase;
import ir.maktab.hibernate.projects.article.features.usermanagement.usecases.ChangeUsernameUseCase;

import java.util.ArrayList;
import java.util.Arrays;

public class EditProfileMenu extends Menu {

    public EditProfileMenu() {
        super();
    }

    @Override
    public void execute() {

        command = "";

        while (!command.equals(Actions.back.name())) {

            displayMenu();
            command = Users.takeCommand(actions);

            if (command.equals(Actions.exit.name())) {
                exit();
            }

            if (command.equals(Actions.username.name())) {
                ChangeUsernameUseCase changeUsernameUseCase
                        = new ChangeUsernameUseCaseImpl();
                changeUsernameUseCase.change(Users.takeNewUsername());
            } else if (command.equals(Actions.nationcode.name())) {
                ChangeNationalCodeUseCase changeNationalCodeUseCase
                        = new ChangeNationalCodeUseCaseImpl();
                changeNationalCodeUseCase.change(Users.takeNewNationalCode());
            } else if (command.equals(Actions.birthday.name())) {
                ChangeBirthdayUseCase changeBirthdayUseCase
                        = new ChangeBirthdayUseCaseImpl();
                changeBirthdayUseCase.change(Users.takeNewBirthday());
            } else if (command.equals(Actions.password.name())) {
                ChangePasswordUseCase changePasswordUseCase
                        = new ChangePasswordUseCaseImpl();
                changePasswordUseCase.change(Users.takeNewPassword());
            }
        }
    }

    @Override
    protected void displayMenu() {
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|                       Edit Profile Menu                       |");
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|  username        ---->    Edit Username.                      |");
        System.out.println("\t|  nationcode      ---->    Edit National Code.                 |");
        System.out.println("\t|  birthday        ---->    Edit Birthday.                      |");
        System.out.println("\t|  password        ---->    Edit Password.                      |");
        System.out.println("\t|  back            ---->    Back to Last Menu.                  |");
        System.out.println("\t|  exit            ---->    Exit.                               |");
        System.out.println("\t+---------------------------------------------------------------+");
    }

    @Override
    protected void setActions() {
        actions = new ArrayList<>(
                Arrays.asList(
                        Actions.username.name()
                        , Actions.nationcode.name()
                        , Actions.birthday.name()
                        , Actions.password.name()
                        , Actions.back.name()
                        , Actions.exit.name()));
    }
}
