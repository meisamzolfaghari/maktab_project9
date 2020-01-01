package ir.maktab.hibernate.projects.article.core.menus.admin;

import ir.maktab.hibernate.projects.article.core.Actions;
import ir.maktab.hibernate.projects.article.core.functions.Categories;
import ir.maktab.hibernate.projects.article.core.menus.Menu;
import ir.maktab.hibernate.projects.article.entities.Category;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindCategoryMenu extends Menu {

    private List<Category> categories;

    public FindCategoryMenu(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public void execute() {
        command = "";
        while (!command.equals(Actions.back.name())) {

            if (!categories.isEmpty())
                Categories.displayAll(categories);
            else {
                System.out.println("\t\u26A0 Category Title not Found!");
                break;
            }

            takeCommand();

            if (command.equals(Actions.exit.name())) {
                System.out.println("\n bye bye!");
                System.exit(0);
            }
            else if (command.equals(Actions.add.name())) {
                Categories.add();
            }
            else if (command.equals(Actions.delete.name())) {
                Category chosenCategory = Categories.choose(categories);
                Categories.delete(chosenCategory);
            }
        }
    }

    @Override
    protected void displayMenu() {
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|                    Find Category Menu                         |");
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|  delete          ---->    Delete Category.                    |");
        System.out.println("\t|  back            ---->    Back to Category Menu.              |");
        System.out.println("\t|  exit            ---->    Exit.                               |");
        System.out.println("\t+---------------------------------------------------------------+");
    }

    @Override
    protected void setActions() {
        actions = new ArrayList<>(
                Arrays.asList(
                        Actions.delete.name()
                        , Actions.back.name()
                        , Actions.exit.name()));
    }
}
