package ir.maktab.hibernate.projects.article.core.menus.admin;

import ir.maktab.hibernate.projects.article.core.Actions;
import ir.maktab.hibernate.projects.article.core.functions.Articles;
import ir.maktab.hibernate.projects.article.core.menus.Menu;
import ir.maktab.hibernate.projects.article.entities.Article;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class AdminChooseArticleMenu extends Menu {

    private Article chosenArticle;

    public AdminChooseArticleMenu(Article chosenArticle) {
        this.chosenArticle = chosenArticle;
    }

    @Override
    public void execute() {

        command = "";
        while (!command.equals(Actions.back.name())) {

            Articles.displayShortVersion(chosenArticle);

            takeCommand();


            if (command.equals(Actions.exit.name())) {
                System.out.println("\n bye bye!");
                System.exit(0);
            }

            Date currentDate = new Date(System.currentTimeMillis());

            if (command.equals(Actions.publish.name())) {
                Articles.publish(chosenArticle , currentDate);
            }
            else if (command.equals(Actions.unpublish.name())) {
                Articles.unpublish(chosenArticle , currentDate);
            }
            else if (command.equals(Actions.delete.name())) {
                Articles.delete(chosenArticle);
            }
        }
    }

    @Override
    protected void displayMenu() {
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|                    Admin Choose Article Menu                  |");
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|  publish         ---->    Publish Chosen Article.             |");
        System.out.println("\t|  unpublish       ---->    Unpublish Chosen Article.           |");
        System.out.println("\t|  delete          ---->    Delete Chosen Article.              |");
        System.out.println("\t|  back            ---->    Back to Admin Article Menu.         |");
        System.out.println("\t|  exit            ---->    Exit.                               |");
        System.out.println("\t+---------------------------------------------------------------+");
    }

    @Override
    protected void setActions() {
        actions = new ArrayList<>(
                Arrays.asList(
                        Actions.publish.name()
                        , Actions.unpublish.name()
                        , Actions.delete.name()
                        , Actions.back.name()
                        , Actions.exit.name()));
    }

}
