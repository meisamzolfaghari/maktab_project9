package ir.maktab.hibernate.projects.article.userinterface.functions;

import ir.maktab.hibernate.projects.article.entities.Tag;

import java.util.List;
import java.util.Scanner;

public class Tags {
    private static Scanner in1 = new Scanner(System.in);
    private static Scanner in2 = new Scanner(System.in);


    public static Tag choose(List<Tag> tags) {
        if (tags.isEmpty()) return null;

        Long id;

        System.out.print("\t\u29bf ID : ");
        id = in2.nextLong();
        for (Tag tag : tags)
            if (id.equals(tag.getId()))
                return tag;

        System.out.println("\t\u26a0 Invalid ID!\n");
        return null;
    }

    public static void displayAll(List<Tag> tags) {
        if (tags.isEmpty()) return;

        System.out.println("------------------------------------------------------------------------------------"
                + "                                All Tags");
        tags.forEach(tag -> System.out.println(
                ""
                        + "------------------------------------------------------------------------------------"
                        + "\n ID= " + tag.getId()
                        + " , Title= '" + tag.getTitle()
                        + "'\n------------------------------------------------------------------------------------"));
    }

    public static String takeTitle() {
        System.out.print("\t\u29bf Title to Find : ");
        String title = in1.nextLine();
        return title;
    }

}
