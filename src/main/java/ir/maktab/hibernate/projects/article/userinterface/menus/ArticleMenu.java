package ir.maktab.hibernate.projects.article.userinterface.menus;

import ir.maktab.hibernate.projects.article.core.Actions;
import ir.maktab.hibernate.projects.article.core.AllRoles;
import ir.maktab.hibernate.projects.article.userinterface.functions.Articles;
import ir.maktab.hibernate.projects.article.userinterface.functions.Users;
import ir.maktab.hibernate.projects.article.userinterface.menus.manager.ManagerChoosePublishArticleMenu;
import ir.maktab.hibernate.projects.article.entities.Article;
import ir.maktab.hibernate.projects.article.features.articlemanagement.impls.FindAllPublishedArticleUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.articlemanagement.usecases.FindAllPublishedArticleUseCase;
import ir.maktab.hibernate.projects.article.features.rolemanagement.impls.FindRoleByTitleUseCaseImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArticleMenu extends Menu {

    public ArticleMenu() {
        super();
    }

    @Override
    public void execute() {
        FindAllPublishedArticleUseCase findAllPublishedArticleUseCase
                = new FindAllPublishedArticleUseCaseImpl();

        command = "";
        while (!command.equals(Actions.back.name())) {

            List<Article> articles = findAllPublishedArticleUseCase.list();

            if (!articles.isEmpty())
                Articles.displayAll(articles);
            else {
                System.out.println("\t\u26A0 Articles List is empty!");
                break;
            }

            displayMenu();
            command = Users.takeCommand(actions);

            if (command.equals(Actions.exit.name())) {
                exit();
            } else if (command.equals(Actions.choose.name())) {

                Article chosenArticle = Articles.choose(articles);
                if (chosenArticle != null) {
                    if (loginUser != null) {
                        if (chosenArticle.getUser().equals(loginUser)) {
                            Menu menu = new UserChooseArticleMenu(chosenArticle);
                            menu.execute();
                        } else if (loginUser.getRoles()
                                .contains(new FindRoleByTitleUseCaseImpl()
                                        .find(AllRoles.manager.name()))) {
                            Menu menu = new ManagerChoosePublishArticleMenu(chosenArticle);
                            menu.execute();
                        }
                    }
                } else {
                    Articles.displayShortVersion(chosenArticle);
                    Menu menu = new FinalMenu();
                    menu.execute();
                }
            }
        }
    }

    @Override
    protected void displayMenu() {
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|                    Article Menu                               |");
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|  choose          ---->    See All Published Articles.         |");
        System.out.println("\t|  back            ---->    Back to Last Menu.                  |");
        System.out.println("\t|  exit            ---->    Exit.                               |");
        System.out.println("\t+---------------------------------------------------------------+");
    }

    @Override
    protected void setActions() {
        actions = new ArrayList<>(
                Arrays.asList(
                        Actions.choose.name()
                        , Actions.back.name()
                        , Actions.exit.name()));
    }


}
