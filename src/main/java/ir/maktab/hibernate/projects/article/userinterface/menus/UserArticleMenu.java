package ir.maktab.hibernate.projects.article.userinterface.menus;

import ir.maktab.hibernate.projects.article.core.Actions;
import ir.maktab.hibernate.projects.article.userinterface.functions.Articles;
import ir.maktab.hibernate.projects.article.userinterface.functions.Users;
import ir.maktab.hibernate.projects.article.entities.Article;
import ir.maktab.hibernate.projects.article.features.articlemanagement.impls.AddArticleUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.articlemanagement.impls.DeleteArticleUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.articlemanagement.impls.FindUserArticleUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.articlemanagement.usecases.AddArticleUseCase;
import ir.maktab.hibernate.projects.article.features.articlemanagement.usecases.DeleteArticleUseCase;
import ir.maktab.hibernate.projects.article.features.articlemanagement.usecases.FindUserArticleUseCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserArticleMenu extends Menu {

    public UserArticleMenu() {
        setActions();
    }

    @Override
    public void execute() {

        FindUserArticleUseCase findUserArticleUseCase
                = new FindUserArticleUseCaseImpl();

        command = "";
        while (!command.equals(Actions.back.name())) {

            List<Article> articles = findUserArticleUseCase
                    .findAll(loginUser);

            if (!articles.isEmpty())
                Articles.displayAll(articles);
            else
                System.out.println("\t\u26A0 You didn't Add any Article yet!");

            displayMenu();
            command = Users.takeCommand(actions);

            if (command.equals(Actions.exit.name())) {
                exit();
            } else if (command.equals(Actions.choose.name())) {
                if (!articles.isEmpty()) {
                    Article chosenArticle = Articles.choose(articles);
                    if (chosenArticle != null) {
                        new UserChooseArticleMenu(chosenArticle).execute();
                    }
                } else
                    System.out.println("\t\u26A0 Add an Article First!");

            } else if (command.equals(Actions.edit.name())) {
                if (!articles.isEmpty()) {
                    Article chosenArticle = Articles.choose(articles);
                    if (chosenArticle != null) {
                        new UserEditArticleMenu(chosenArticle).execute();
                    }
                } else
                    System.out.println("\t\u26A0 Add an Article First!");
            } else if (command.equals(Actions.add.name())) {
                AddArticleUseCase addArticleUseCase
                        = new AddArticleUseCaseImpl();
                addArticleUseCase.add(Articles.takeTitle(), Articles.takeBrief()
                        , Articles.takeContent(), Articles.takeCurrentTime(), loginUser
                        , Articles.takeCategory(), Articles.takeTags());
            } else if (command.equals(Actions.delete.name())) {
                if (!articles.isEmpty()) {
                    DeleteArticleUseCase deleteArticleUseCase
                            = new DeleteArticleUseCaseImpl();
                    Article chosenArticle = Articles.choose(articles);
                    if (chosenArticle != null)
                        deleteArticleUseCase.delete(chosenArticle);
                } else
                    System.out.println("\t\u26A0 Article List is Empty!");
            }
        }
    }

    @Override
    protected void displayMenu() {
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|                   User Article Menu                           |");
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|  choose          ---->    Choose Article.                     |");
        System.out.println("\t|  edit            ---->    Edit chosen Article.                |");
        System.out.println("\t|  add             ---->    Insert a new Article.               |");
        System.out.println("\t|  delete          ---->    delete Article.                     |");
        System.out.println("\t|  back            ---->    Back to Last Menu.                  |");
        System.out.println("\t|  exit            ---->    Exit.                               |");
        System.out.println("\t+---------------------------------------------------------------+");
    }

    @Override
    protected void setActions() {
        actions = new ArrayList<>(
                Arrays.asList(
                        Actions.choose.name()
                        , Actions.edit.name()
                        , Actions.add.name()
                        , Actions.delete.name()
                        , Actions.back.name()
                        , Actions.exit.name()));
    }
}
