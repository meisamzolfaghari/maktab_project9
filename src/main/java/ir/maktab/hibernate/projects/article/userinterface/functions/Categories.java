package ir.maktab.hibernate.projects.article.userinterface.functions;

import ir.maktab.hibernate.projects.article.entities.Category;

import java.util.List;
import java.util.Scanner;

public class Categories {
    private static Scanner in1 = new Scanner(System.in);
    private static Scanner in2 = new Scanner(System.in);

    public static Category choose(List<Category> categories ){

        if (categories.isEmpty()) return null;

        Long id;

        System.out.print("\t\u29bf ID : ");
        id = in2.nextLong();
        for (Category category : categories)
            if (id.equals(category.getId()))
                return category;

        System.out.println("\t\u26a0 Invalid ID!\n");
        return null;
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

    public static String takeTitle() {
        System.out.print("\t\u29bf Title >>> ");
        String title = in1.nextLine();
        return title;
    }

    public static String takeDescription() {
        System.out.print("\t\u29bf Description >>> ");
        String description = in1.nextLine();
        return description;
    }

}
