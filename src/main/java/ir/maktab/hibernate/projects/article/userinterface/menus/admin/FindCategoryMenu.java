package ir.maktab.hibernate.projects.article.userinterface.menus.admin;

import ir.maktab.hibernate.projects.article.core.Actions;
import ir.maktab.hibernate.projects.article.core.share.AuthenticationService;
import ir.maktab.hibernate.projects.article.userinterface.functions.Categories;
import ir.maktab.hibernate.projects.article.userinterface.functions.Users;
import ir.maktab.hibernate.projects.article.userinterface.menus.Menu;
import ir.maktab.hibernate.projects.article.entities.Category;
import ir.maktab.hibernate.projects.article.features.categorymanagement.impls.DeleteCategoryUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.categorymanagement.usecases.DeleteCategoryUseCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindCategoryMenu extends Menu {

    private List<Category> categories;

    public FindCategoryMenu(List<Category> categories) {
        this.categories = categories;
        setActions();
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

            displayMenu();
            command = Users.takeCommand(actions);

            if (command.equals(Actions.exit.name())) {
                exit();
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
        System.out.println("\t|                    Find Category Menu                         |");
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|  delete          ---->    Delete Category.                    |");
        System.out.println("\t|  back            ---->    Back to Last Menu.                  |");
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
