package ir.maktab.hibernate.projects.article.userinterface.menus;

import ir.maktab.hibernate.projects.article.core.Actions;
import ir.maktab.hibernate.projects.article.core.share.AuthenticationService;
import ir.maktab.hibernate.projects.article.userinterface.functions.Articles;
import ir.maktab.hibernate.projects.article.userinterface.functions.Users;
import ir.maktab.hibernate.projects.article.entities.Article;
import ir.maktab.hibernate.projects.article.features.articlemanagement.impls.DeleteArticleUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.articlemanagement.usecases.DeleteArticleUseCase;

import java.util.ArrayList;
import java.util.Arrays;

public class UserChooseArticleMenu extends Menu {
    private Article chosenArticle;

    public UserChooseArticleMenu(Article chosenArticle) {
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

            else if (command.equals(Actions.edit.name())) {
                new UserEditArticleMenu(chosenArticle).execute();
            }

            else if (command.equals(Actions.delete.name())) {
                DeleteArticleUseCase deleteArticleUseCase
                        = new DeleteArticleUseCaseImpl();
                deleteArticleUseCase.delete(chosenArticle);
                break;
            }
        }
    }

    @Override
    protected void displayMenu() {
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|                   User Choose Article Menu                    |");
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|  edit            ---->    Edit Article.                       |");
        System.out.println("\t|  delete          ---->    delete Article.                     |");
        System.out.println("\t|  back            ---->    Back to Last Menu.                  |");
        System.out.println("\t|  exit            ---->    Exit.                               |");
        System.out.println("\t+---------------------------------------------------------------+");
    }

    @Override
    protected void setActions() {
        actions = new ArrayList<>(
                Arrays.asList(
                        Actions.edit.name()
                        , Actions.delete.name()
                        , Actions.back.name()
                        , Actions.exit.name()));
    }
}
