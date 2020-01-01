package ir.maktab.hibernate.projects.article.core.menus;

import ir.maktab.hibernate.projects.article.core.Actions;
import ir.maktab.hibernate.projects.article.core.functions.Categories;
import ir.maktab.hibernate.projects.article.entities.Category;
import ir.maktab.hibernate.projects.article.features.articlemanagement.impl.AddCategoryByUserUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.articlemanagement.impl.FindAllCategoriesUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.articlemanagement.usecases.AddCategoryByUserUseCase;
import ir.maktab.hibernate.projects.article.features.articlemanagement.usecases.FindAllCategoriesUseCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoryMenu extends Menu {

    private Category chosenCategory;

    @Override
    public void execute() {

        command = "";

        while (!command.equals("choose")) {
            FindAllCategoriesUseCase findAllCategoriesUseCase
                    = new FindAllCategoriesUseCaseImpl();

            List<Category> categories = findAllCategoriesUseCase.list();

            if (!categories.isEmpty())
                Categories.display(categories);
            else
                System.out.println("\t\u26A0 Category List is empty!");

            takeCommand();

            if (command.equals(Actions.choose.name())) {
                if (!categories.isEmpty())
                    setChosenCategory(Categories.choose(categories));
                else
                    System.out.println("\t\u26A0 Add a Category First.");

            }

            else if (command.equals(Actions.add.name())) {
                AddCategoryByUserUseCase addCategoryByUserUseCase
                        = new AddCategoryByUserUseCaseImpl();

                Category categoryToAdd = Categories.add();

                Category addedCategory = addCategoryByUserUseCase.add(categoryToAdd);

                if (categoryToAdd.getTitle().equals(addedCategory.getTitle()))
                    System.out.println("\t\u2714 Category successfully Added.\n");
                else
                    System.out.println("\t\u274c Failed to Add Category!\n");
            }
        }
    }

    @Override
    protected void displayMenu() {
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|                       Category Menu                           |");
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|  choose          ---->    choose a category.                  |");
        System.out.println("\t|  add             ---->    add a category.                     |");
        System.out.println("\t+---------------------------------------------------------------+");
    }

    @Override
    protected void setActions() {
        actions = new ArrayList<>(
                Arrays.asList(
                        Actions.choose.name()
                        , Actions.add.name()));
    }

    private void setChosenCategory(Category chosenCategory) {
        this.chosenCategory = chosenCategory;
    }

    public Category getChosenCategory() {
        return chosenCategory;
    }
}
