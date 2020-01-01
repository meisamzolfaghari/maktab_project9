package ir.maktab.hibernate.projects.article.core.functions;

import ir.maktab.hibernate.projects.article.entities.Category;
import ir.maktab.hibernate.projects.article.entities.Tag;
import ir.maktab.hibernate.projects.article.repositories.CategoryRepository;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Categories {

    public static Category choose(List<Category> categories ){

        if (categories.isEmpty()) return null;

        Scanner in = new Scanner(System.in);

        Integer id;

        do {
            System.out.print("\t\u29bf ID : ");
            id = in.nextInt();
            for (Category category : categories)
                if (id.equals(category.getId()))
                    return category;

            System.out.println("\t\u26a0 Invalid ID! Try again...\n");

        } while (true);
    }

    public static void displayAll(List<Category> categories) {
        if (categories.isEmpty()) return;

        System.out.println("------------------------------------------------------------------------------------"
                + "\n                           Categories");
        categories.forEach(category -> System.out.println(
                ""
                        + "------------------------------------------------------------------------------------"
                        + "\n ID= " + category.getId()
                        + " , Category Title= '" + category.getTitle()
                        + "' , description= '" + category.getDescription()
                        + "'\n------------------------------------------------------------------------------------"));
    }

    public static List<Category> findTitle(List<Category> categories) {
        Scanner in = new Scanner(System.in);
        System.out.print("\t\u29bf Title to Find : ");
        String title = in.nextLine();
        List<Category> foundCategories = categories.stream()
                .filter(category -> category.getTitle().contains(title))
                .collect(Collectors.toList());
        return foundCategories;
    }

    public static void add() {
        Scanner in = new Scanner(System.in);
        CategoryRepository categoryRepository = CategoryRepository.getInstance();
        Category categoryToAdd = new Category();

        System.out.print("\t\u29bf Title : ");
        String title = in.nextLine();

        System.out.print("\t\u29bf Description : ");
        String description = in.nextLine();

        categoryRepository.save(categoryToAdd);

        Category addedCategory = categoryRepository.findById(categoryToAdd.getId());
        if (addedCategory != null)
            System.out.println("\t\u2714 Category successfully Added.\n");
        else
            System.out.println("\t\u274c Failed to Add Category!\n");

    }

    public static void delete(Category categoryToDelete) {
        CategoryRepository categoryRepository = CategoryRepository.getInstance();

        categoryRepository.save(categoryToDelete);

        Category deletedCategory = categoryRepository.findById(categoryToDelete.getId());
        if (deletedCategory == null)
            System.out.println("\t\u2714 Category successfully Deleted.\n");
        else
            System.out.println("\t\u274c Failed to Delete Category!\n");

    }

}
