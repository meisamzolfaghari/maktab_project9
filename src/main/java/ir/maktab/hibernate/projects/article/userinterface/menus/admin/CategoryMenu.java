package ir.maktab.hibernate.projects.article.userinterface.menus.admin;

import ir.maktab.hibernate.projects.article.core.Actions;
import ir.maktab.hibernate.projects.article.userinterface.functions.Categories;
import ir.maktab.hibernate.projects.article.userinterface.functions.Users;
import ir.maktab.hibernate.projects.article.userinterface.menus.Menu;
import ir.maktab.hibernate.projects.article.entities.Category;
import ir.maktab.hibernate.projects.article.features.categorymanagement.impls.AddCategoryUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.categorymanagement.impls.DeleteCategoryUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.categorymanagement.impls.FindAllCategoryUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.categorymanagement.impls.FindCategoryByTitleUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.categorymanagement.usecases.AddCategoryUseCase;
import ir.maktab.hibernate.projects.article.features.categorymanagement.usecases.DeleteCategoryUseCase;
import ir.maktab.hibernate.projects.article.features.categorymanagement.usecases.FindAllCategoryUseCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoryMenu extends Menu {

    public CategoryMenu() {
        setActions();
    }

    @Override
    public void execute() {
        command = "";
        while (!command.equals(Actions.back.name())) {

            FindAllCategoryUseCase findAllCategoryUseCase
                    = new FindAllCategoryUseCaseImpl();

            List<Category> categories = findAllCategoryUseCase.list();

            if (!categories.isEmpty())
                Categories.displayAll(categories);
            else {
                System.out.println("\t\u26A0 Categories List is empty!");
                break;
            }

            displayMenu();
            command = Users.takeCommand(actions);

            if (command.equals(Actions.exit.name())) {
                exit();
            }

            if (command.equals(Actions.find.name())) {
                new FindCategoryMenu(new FindCategoryByTitleUseCaseImpl()
                        .list(Categories.takeTitle())).execute();
            } else if (command.equals(Actions.add.name())) {
                AddCategoryUseCase addCategoryUseCase
                        = new AddCategoryUseCaseImpl();
                addCategoryUseCase.add(Categories.takeTitle(), Categories.takeDescription());
            } else if (command.equals(Actions.delete.name())) {
                Category chosenCategory = Categories.choose(categories);
                if (chosenCategory != null) {
                    DeleteCategoryUseCase deleteCategoryUseCase
                            = new DeleteCategoryUseCaseImpl();
                    deleteCategoryUseCase.delete(chosenCategory);
                }
            }
        }
    }

    @Override
    protected void displayMenu() {
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|                    Category Menu                              |");
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|  find            ---->    Find Category By Title.             |");
        System.out.println("\t|  add             ---->    Add Category.                       |");
        System.out.println("\t|  delete          ---->    Delete Category.                    |");
        System.out.println("\t|  back            ---->    Back to Last Menu.                  |");
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
