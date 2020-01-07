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
        super();
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
            Menu menu;
            if (command.equals(Actions.users.name())) {
                menu = new AdminUserMenu();
                menu.execute();
            } else if (command.equals(Actions.tags.name())) {
                menu = new TagMenu();
                menu.execute();
            } else if (command.equals(Actions.categories.name())) {
                menu = new CategoryMenu();
                menu.execute();
            } else if (command.equals(Actions.publisharticles.name())) {
                menu = new ManagerPublishArticleMenu();
                menu.execute();
            } else if (command.equals(Actions.myarticles.name())) {
                menu = new UserArticleMenu();
                menu.execute();
            } else if (command.equals(Actions.articles.name())) {
                if (loginUser.getRoles()
                        .contains(new FindRoleByTitleUseCaseImpl().find(AllRoles.admin.name()))) {
                    menu = new AdminArticleMenu();
                    menu.execute();
                } else {
                    menu = new ArticleMenu();
                    menu.execute();
                }
            } else if (command.equals(Actions.profile.name())) {
                menu = new ProfileMenu();
                menu.execute();
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
        if (loginUser.getRoles()
                .contains(new FindRoleByTitleUseCaseImpl().find(AllRoles.manager.name())))
            System.out.println("\t|  publisharticles ---->    See And Publish Articles.           |");
        else if (loginUser.getRoles()
                .contains(new FindRoleByTitleUseCaseImpl().find(AllRoles.admin.name()))) {
            System.out.println("\t|  articles        ---->    See and Edit all Articles.          |");
            System.out.println("\t|  users           ---->    See and Edit all Users.             |");
            System.out.println("\t|  tags            ---->    Add or Delete Tags.                 |");
            System.out.println("\t|  categories      ---->    Add or Delete Categories.           |");
        }
        System.out.println("\t|  myarticles      ---->    See Your Articles.                  |");
        if (!loginUser.getRoles()
                .contains(new FindRoleByTitleUseCaseImpl().find(AllRoles.admin.name())))
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

        if (loginUser.getRoles()
                .contains(new FindRoleByTitleUseCaseImpl().find(AllRoles.manager.name())))
            actions.add(Actions.publisharticles.name());
        else if (loginUser.getRoles()
                .contains(new FindRoleByTitleUseCaseImpl().find(AllRoles.admin.name()))) {
            actions.add(Actions.users.name());
            actions.add(Actions.tags.name());
            actions.add(Actions.categories.name());
        }
    }
}
