package ir.maktab.hibernate.projects.article.core.menus;

import ir.maktab.hibernate.projects.article.core.Actions;
import ir.maktab.hibernate.projects.article.core.functions.Articles;
import ir.maktab.hibernate.projects.article.entities.Article;
import ir.maktab.hibernate.projects.article.repositories.ArticleRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArticleMenu extends Menu {
    @Override
    public void execute() {

        ArticleRepository articleRepository = ArticleRepository.getInstance();

        command = "";
        while (!command.equals(Actions.back.name())) {


            List<Article> articles = new ArrayList<>();

            articleRepository.findAll().stream()
                    .filter(Article::isPublished)
                    .forEach(articles::add);

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

            else if (command.equals(Actions.choose.name())) {

                Articles.displayShortVersion(Articles.choose(articles));

                new FinalMenu().execute();
            }

        }
    }

    @Override
    protected void displayMenu() {
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|                    Article Menu                               |");
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|  choose          ---->    See your Article.                   |");
        System.out.println("\t|  back            ---->    Back to User Menu.                  |");
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
