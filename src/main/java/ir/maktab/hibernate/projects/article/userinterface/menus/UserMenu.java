package ir.maktab.hibernate.projects.article.userinterface.menus;

import ir.maktab.hibernate.projects.article.core.Actions;
import ir.maktab.hibernate.projects.article.core.AllRoles;
import ir.maktab.hibernate.projects.article.userinterface.functions.Users;
import ir.maktab.hibernate.projects.article.userinterface.menus.admin.AdminArticleMenu;
import ir.maktab.hibernate.projects.article.userinterface.menus.admin.AdminUserMenu;
import ir.maktab.hibernate.projects.article.userinterface.menus.admin.CategoryMenu;
import ir.maktab.hibernate.projects.article.userinterface.menus.admin.TagMenu;
import ir.maktab.hibernate.projects.article.userinterface.menus.manager.ManagerPublishArticleMenu;
import ir.maktab.hibernate.projects.article.features.rolemanagement.impls.FindRoleByTitleUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.usermanagement.impls.LogoutUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.usermanagement.usecases.LogoutUseCase;

import java.util.ArrayList;
import java.util.Arrays;

public class UserMenu extends Menu {

    public UserMenu() {
        setActions();
    }

    @Override
    public void execute() {
        command = "";

        while (!command.equals(Actions.logout.name())) {

            displayMenu();
            command = Users.takeCommand(actions);

            if (command.equals(Actions.exit.name())) {
                exit();
            }
            if (command.equals(Actions.users.name())) {
                new AdminUserMenu().execute();
            } else if (command.equals(Actions.tags.name())) {
                new TagMenu().execute();
            } else if (command.equals(Actions.categories.name())) {
                new CategoryMenu().execute();
            } else if (command.equals(Actions.publisharticles.name())) {
                new ManagerPublishArticleMenu().execute();
            } else if (command.equals(Actions.myarticles.name())) {
                new UserArticleMenu().execute();
            } else if (command.equals(Actions.articles.name())) {
                if (Users.isAdmin(loginUser)) {
                    new AdminArticleMenu().execute();
                } else {
                    new ArticleMenu().execute();
                }
            } else if (command.equals(Actions.profile.name())) {
                new ProfileMenu().execute();
            } else if (command.equals(Actions.logout.name())) {
                LogoutUseCase logoutUseCase = new LogoutUseCaseImpl();
                logoutUseCase.logout();
            }
        }
    }

    @Override
    protected void displayMenu() {
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|                      User Menu                                |");
        System.out.println("\t+---------------------------------------------------------------+");
        if (Users.isAdmin(loginUser)) {
            System.out.println("\t|  articles        ---->    See and Edit all Articles.          |");
            System.out.println("\t|  users           ---->    See and Edit all Users.             |");
            System.out.println("\t|  tags            ---->    Add or Delete Tags.                 |");
            System.out.println("\t|  categories      ---->    Add or Delete Categories.           |");
        } else if (Users.isManager(loginUser))
            System.out.println("\t|  publisharticles ---->    See And Publish Articles.           |");
        System.out.println("\t|  myarticles      ---->    See Your Articles.                  |");
        if (!Users.isAdmin(loginUser))
            System.out.println("\t|  articles        ---->    See All Articles.                   |");
        System.out.println("\t|  profile         ---->    See your Profile.                   |");
        System.out.println("\t|  logout          ---->    Logout.                             |");
        System.out.println("\t|  exit            ---->    Exit.                               |");
        System.out.println("\t+---------------------------------------------------------------+");
    }

    @Override
    protected void setActions() {
        actions = new ArrayList<>(
                Arrays.asList(
                        Actions.myarticles.name()
                        , Actions.articles.name()
                        , Actions.profile.name()
                        , Actions.logout.name()
                        , Actions.exit.name()));

        if (Users.isAdmin(loginUser)) {
            actions.add(Actions.users.name());
            actions.add(Actions.tags.name());
            actions.add(Actions.categories.name());
        } else if (Users.isManager(loginUser))
            actions.add(Actions.publisharticles.name());

    }
}
