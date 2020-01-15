package ir.maktab.hibernate.projects.article.userinterface.menus.manager;

import ir.maktab.hibernate.projects.article.core.Actions;
import ir.maktab.hibernate.projects.article.core.share.AuthenticationService;
import ir.maktab.hibernate.projects.article.userinterface.functions.Articles;
import ir.maktab.hibernate.projects.article.userinterface.functions.Users;
import ir.maktab.hibernate.projects.article.userinterface.menus.Menu;
import ir.maktab.hibernate.projects.article.entities.Article;
import ir.maktab.hibernate.projects.article.features.articlemanagement.impls.PublishArticleUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.articlemanagement.impls.UnPublishArticleUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.articlemanagement.usecases.PublishArticleUseCase;
import ir.maktab.hibernate.projects.article.features.articlemanagement.usecases.UnPublishArticleUseCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class ManagerChoosePublishArticleMenu extends Menu {
    private Article chosenArticle;

    public ManagerChoosePublishArticleMenu(Article chosenArticle) {
        this.chosenArticle = chosenArticle;
        setActions();
    }

    @Override
    public void execute() {

        command = "";
        while (!command.equals(Actions.back.name())) {

            Articles.displayFullVersion(chosenArticle);

            displayMenu();
            command = Users.takeCommand(actions);

            if (command.equals(Actions.exit.name())) {
                exit();
            }

            Date currentDate = new Date(System.currentTimeMillis());

            if (command.equals(Actions.publish.name())) {
                PublishArticleUseCase publishArticleUseCase
                        = new PublishArticleUseCaseImpl();
                publishArticleUseCase.publish(chosenArticle, currentDate);
                break;
            } else if (command.equals(Actions.unpublish.name())) {
                UnPublishArticleUseCase unPublishArticleUseCase
                        = new UnPublishArticleUseCaseImpl();
                unPublishArticleUseCase.unPublish(chosenArticle, currentDate);
                break;
            }
        }
    }

    @Override
    protected void displayMenu() {
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|                Manager Choose Article Menu                    |");
        System.out.println("\t+---------------------------------------------------------------+");
        if (!chosenArticle.isPublished())
            System.out.println("\t|  publish         ---->    Publish Article.                    |");
        else System.out.println("\t|  unpublish       ---->    UnPublish Article.                  |");
        System.out.println("\t|  back            ---->    Back to Last Menu.                  |");
        System.out.println("\t|  exit            ---->    Exit.                               |");
        System.out.println("\t+---------------------------------------------------------------+");
    }

    @Override
    protected void setActions() {
        actions = new ArrayList<>(
                Arrays.asList(
                        Actions.back.name()
                        , Actions.exit.name()));
        if (!chosenArticle.isPublished())
            actions.add(Actions.publish.name());
        else actions.add(Actions.unpublish.name());

    }

}
