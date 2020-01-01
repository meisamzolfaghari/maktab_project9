package ir.maktab.hibernate.projects.article.core.menus.admin;

import ir.maktab.hibernate.projects.article.core.Actions;
import ir.maktab.hibernate.projects.article.core.functions.Articles;
import ir.maktab.hibernate.projects.article.core.menus.Menu;
import ir.maktab.hibernate.projects.article.entities.Article;
import ir.maktab.hibernate.projects.article.repositories.ArticleRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AdminArticleMenu extends Menu {
    @Override
    public void execute() {
        command = "";
        while (!command.equals(Actions.back.name())) {

            ArticleRepository articleRepository = ArticleRepository.getInstance();

            List<Article> articles = articleRepository.findAll();

            if (!articles.isEmpty())
                Articles.displayAll(articles);
            else {
                System.out.println("\t\u26A0 Articles List is empty!");
                break;
            }

            takeCommand();

            if (command.equals(Actions.exit.name())) {
                System.out.println("\n bye bye!");
                System.exit(0);
            }

            Article chosenArticle;
            Date currentDate = new Date(System.currentTimeMillis());

            if (command.equals(Actions.choose.name())) {
                chosenArticle = Articles.choose(articles);
                new AdminChooseArticleMenu(chosenArticle).execute();
            }

            else if (command.equals(Actions.publish.name())) {
                chosenArticle = Articles.choose(articles);
                Articles.publish(chosenArticle , currentDate);
            } else if (command.equals(Actions.unpublish.name())) {
                chosenArticle = Articles.choose(articles);
                Articles.unpublish(chosenArticle , currentDate);
            }
            else if (command.equals(Actions.delete.name())) {
                chosenArticle = Articles.choose(articles);
                Articles.delete(chosenArticle);
            }

        }
    }

    @Override
    protected void displayMenu() {
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|                    Admin Choose Article Menu                  |");
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|  choose          ---->    Choose Article.                     |");
        System.out.println("\t|  publish         ---->    Publish Article.                    |");
        System.out.println("\t|  unpublish       ---->    Unpublish Article.                  |");
        System.out.println("\t|  delete          ---->    Delete Article.                     |");
        System.out.println("\t|  back            ---->    Back to Admin Menu.                 |");
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
