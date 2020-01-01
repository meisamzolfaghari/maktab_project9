package ir.maktab.hibernate.projects.article.core.menus;

import ir.maktab.hibernate.projects.article.core.Actions;
import ir.maktab.hibernate.projects.article.core.functions.Articles;
import ir.maktab.hibernate.projects.article.entities.Article;
import ir.maktab.hibernate.projects.article.features.articlemanagement.impl.AddArticlesByUserUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.articlemanagement.impl.FindUserArticlesByUserUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.articlemanagement.usecases.AddArticleByUserUseCase;
import ir.maktab.hibernate.projects.article.features.articlemanagement.usecases.FindUserArticlesByUserUseCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserArticleMenu extends Menu {
    @Override
    public void execute() {

        command = "";
        while (!command.equals(Actions.back.name())) {

            FindUserArticlesByUserUseCase findUserArticlesByUserUseCase
                    = new FindUserArticlesByUserUseCaseImpl();

            List<Article> articles = findUserArticlesByUserUseCase.list();

            if (!articles.isEmpty())
                Articles.displayAll(articles);
            else
                System.out.println("\t\u26A0 You didn't Add any Article yet!");

            takeCommand();

            if (command.equals(Actions.exit.name())) {
                System.out.println("\n bye bye!");
                System.exit(0);
            }

            else if (command.equals(Actions.see.name())) {
                if (!articles.isEmpty()) {
                    Article chosenArticle = Articles.choose(articles);

                    Articles.display(chosenArticle);

                    new FinalMenu().execute();
                }
                else
                    System.out.println("\t\u26A0 Add an Article First!");

            }

            else if (command.equals(Actions.edit.name())) {
                if (!articles.isEmpty()) {

                    Article chosenArticle = Articles.choose(articles);

                    Menu editArticleMenu = new EditArticleMenu(chosenArticle);
                    editArticleMenu.execute();
                }
                else
                    System.out.println("\t\u26A0 Add an Article First!");
            }

            else if (command.equals(Actions.add.name())) {
                AddArticleByUserUseCase addArticleByUserUseCase
                        = new AddArticlesByUserUseCaseImpl();

                Article articleToAdd = Articles.add();

                Article addedArticle = addArticleByUserUseCase.add(articleToAdd);

                if (addedArticle != null)
                    System.out.println("\t\u2714 Article successfully Added.\n");
                else
                    System.out.println("\t\u274c failed to Add Article!\n");

            }
        }
    }

    @Override
    protected void displayMenu() {
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|                    User Article Menu                          |");
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|  see             ---->    See your Article.                   |");
        System.out.println("\t|  edit            ---->    Edit your Article.                  |");
        System.out.println("\t|  add             ---->    Insert a new Article.               |");
        System.out.println("\t|  back            ---->    Back to User Menu.                  |");
        System.out.println("\t|  exit            ---->    Exit.                               |");
        System.out.println("\t+---------------------------------------------------------------+");
    }

    @Override
    protected void setActions() {
        actions = new ArrayList<>(
                Arrays.asList(
                        Actions.see.name()
                        , Actions.edit.name()
                        , Actions.add.name()
                        , Actions.back.name()
                        , Actions.exit.name()));
    }
}
