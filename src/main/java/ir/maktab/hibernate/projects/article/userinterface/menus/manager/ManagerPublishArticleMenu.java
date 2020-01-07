package ir.maktab.hibernate.projects.article.userinterface.menus.manager;

import ir.maktab.hibernate.projects.article.core.Actions;
import ir.maktab.hibernate.projects.article.userinterface.functions.Articles;
import ir.maktab.hibernate.projects.article.userinterface.functions.Users;
import ir.maktab.hibernate.projects.article.userinterface.menus.Menu;
import ir.maktab.hibernate.projects.article.entities.Article;
import ir.maktab.hibernate.projects.article.features.articlemanagement.impls.FindAllArticleToPublishByManagerUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.articlemanagement.impls.PublishArticleUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.articlemanagement.usecases.FindAllArticleToPublishByManagerUseCase;
import ir.maktab.hibernate.projects.article.features.articlemanagement.usecases.PublishArticleUseCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ManagerPublishArticleMenu extends Menu {

    public ManagerPublishArticleMenu() {
        super();
    }

    @Override
    public void execute() {
        command = "";
        while (!command.equals(Actions.back.name())) {

            FindAllArticleToPublishByManagerUseCase findAllArticleToPublishByManagerUseCase
                    = new FindAllArticleToPublishByManagerUseCaseImpl();

            List<Article> articles = findAllArticleToPublishByManagerUseCase.list();

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
            }

            Article chosenArticle;
            Date currentDate = new Date(System.currentTimeMillis());

            if (command.equals(Actions.choose.name())) {
                chosenArticle = Articles.choose(articles);
                if (chosenArticle != null) {
                    Menu menu = new ManagerChoosePublishArticleMenu(chosenArticle);
                    menu.execute();
                }
            } else if (command.equals(Actions.publish.name())) {
                chosenArticle = Articles.choose(articles);
                if (chosenArticle != null) {
                    PublishArticleUseCase publishArticleUseCase
                            = new PublishArticleUseCaseImpl();
                    publishArticleUseCase.publish(chosenArticle, currentDate);
                }
            }
        }
    }

    @Override
    protected void displayMenu() {
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|                Manager Publish Article Menu                   |");
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|  choose          ---->    Choose Article.                     |");
        System.out.println("\t|  publish         ---->    Publish Article.                    |");
        System.out.println("\t|  back            ---->    Back to Last Menu.                  |");
        System.out.println("\t|  exit            ---->    Exit.                               |");
        System.out.println("\t+---------------------------------------------------------------+");
    }

    @Override
    protected void setActions() {
        actions = new ArrayList<>(
                Arrays.asList(
                        Actions.choose.name()
                        , Actions.publish.name()
                        , Actions.back.name()
                        , Actions.exit.name()));
    }
}
