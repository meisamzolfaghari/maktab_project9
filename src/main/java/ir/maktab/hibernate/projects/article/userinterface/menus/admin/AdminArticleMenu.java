package ir.maktab.hibernate.projects.article.userinterface.menus.admin;

import ir.maktab.hibernate.projects.article.core.Actions;
import ir.maktab.hibernate.projects.article.entities.Article;
import ir.maktab.hibernate.projects.article.features.articlemanagement.impls.DeleteArticleUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.articlemanagement.impls.FindAllArticleByAdminUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.articlemanagement.impls.PublishArticleUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.articlemanagement.impls.UnPublishArticleUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.articlemanagement.usecases.DeleteArticleUseCase;
import ir.maktab.hibernate.projects.article.features.articlemanagement.usecases.FindAllArticleByAdminUseCase;
import ir.maktab.hibernate.projects.article.features.articlemanagement.usecases.PublishArticleUseCase;
import ir.maktab.hibernate.projects.article.features.articlemanagement.usecases.UnPublishArticleUseCase;
import ir.maktab.hibernate.projects.article.userinterface.functions.Articles;
import ir.maktab.hibernate.projects.article.userinterface.functions.Users;
import ir.maktab.hibernate.projects.article.userinterface.menus.Menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AdminArticleMenu extends Menu {

    public AdminArticleMenu() {
        setActions();
    }

    @Override
    public void execute() {
        command = "";
        while (!command.equals(Actions.back.name())) {

            FindAllArticleByAdminUseCase findAllArticleByAdminUseCase
                    = new FindAllArticleByAdminUseCaseImpl();

            List<Article> articles = findAllArticleByAdminUseCase.list();

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
                    new AdminChooseArticleMenu(chosenArticle).execute();
                }
            } else if (command.equals(Actions.publish.name())) {
                chosenArticle = Articles.choose(articles);
                if (chosenArticle != null) {
                    PublishArticleUseCase publishArticleUseCase
                            = new PublishArticleUseCaseImpl();
                    publishArticleUseCase.publish(chosenArticle, currentDate);
                }
            } else if (command.equals(Actions.unpublish.name())) {
                chosenArticle = Articles.choose(articles);
                if (chosenArticle != null) {
                    UnPublishArticleUseCase unPublishArticleUseCase
                            = new UnPublishArticleUseCaseImpl();
                    unPublishArticleUseCase.unPublish(chosenArticle, currentDate);
                }
            } else if (command.equals(Actions.delete.name())) {
                chosenArticle = Articles.choose(articles);
                if (chosenArticle != null) {
                    DeleteArticleUseCase deleteArticleUseCase
                            = new DeleteArticleUseCaseImpl();
                    deleteArticleUseCase.delete(chosenArticle);
                }
            }
        }
    }

    @Override
    protected void displayMenu() {
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|                    Admin Article Menu                         |");
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|  choose          ---->    Choose Article.                     |");
        System.out.println("\t|  publish         ---->    Publish Article.                    |");
        System.out.println("\t|  unpublish       ---->    UnPublish Article.                  |");
        System.out.println("\t|  delete          ---->    Delete Article.                     |");
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
                        , Actions.unpublish.name()
                        , Actions.delete.name()
                        , Actions.back.name()
                        , Actions.exit.name()));
    }
}
