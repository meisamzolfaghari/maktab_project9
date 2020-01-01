package ir.maktab.hibernate.projects.article.core.menus.admin;

import ir.maktab.hibernate.projects.article.core.Actions;
import ir.maktab.hibernate.projects.article.core.functions.Categories;
import ir.maktab.hibernate.projects.article.core.functions.Tags;
import ir.maktab.hibernate.projects.article.core.menus.Menu;
import ir.maktab.hibernate.projects.article.entities.Category;
import ir.maktab.hibernate.projects.article.entities.Tag;
import ir.maktab.hibernate.projects.article.repositories.CategoryRepository;
import ir.maktab.hibernate.projects.article.repositories.TagRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoryMenu extends Menu {
    @Override
    public void execute() {
        command = "";
        while (!command.equals(Actions.back.name())) {

            CategoryRepository categoryRepository = CategoryRepository.getInstance();

            List<Category> categories = categoryRepository.findAll();

            if (!categories.isEmpty())
                Categories.displayAll(categories);
            else {
                System.out.println("\t\u26A0 Categories List is empty!");
                break;
            }

            takeCommand();

            if (command.equals(Actions.exit.name())) {
                System.out.println("\n bye bye!");
                System.exit(0);
            }


            if (command.equals(Actions.find.name())) {

                new FindCategoryMenu(Categories.findTitle(categories)).execute();
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
        System.out.println("\t|                    Category Menu                              |");
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|  find            ---->    Find Category By Title.             |");
        System.out.println("\t|  add             ---->    Add a Category.                     |");
        System.out.println("\t|  delete          ---->    Delete Category.                    |");
        System.out.println("\t|  back            ---->    Back to Admin Menu.                 |");
        System.out.println("\t|  exit            ---->    Exit.                               |");
        System.out.println("\t+---------------------------------------------------------------+");
    }

    @Override
    protected void setActions() {
        actions = new ArrayList<>(
                Arrays.asList(
                        Actions.find.name()
                        , Actions.add.name()
                        , Actions.delete.name()
                        , Actions.back.name()
                        , Actions.exit.name()));
    }
}
