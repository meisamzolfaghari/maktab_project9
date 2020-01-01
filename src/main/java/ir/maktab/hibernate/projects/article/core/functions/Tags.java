package ir.maktab.hibernate.projects.article.core.functions;

import ir.maktab.hibernate.projects.article.entities.Role;
import ir.maktab.hibernate.projects.article.entities.Tag;
import ir.maktab.hibernate.projects.article.entities.User;
import ir.maktab.hibernate.projects.article.repositories.TagRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Tags {

    public static Tag choose(List<Tag> tags) {
        if (tags.isEmpty()) return null;

        Scanner in = new Scanner(System.in);

        Long id;

        do {
            System.out.print("\t\u29bf ID : ");
            id = in.nextLong();
            for (Tag tag : tags)
                if (id.equals(tag.getId()))
                    return tag;

            System.out.println("\t\u26a0 Invalid ID! Try again...\n");

        } while (true);
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

    public static List<Tag> findTitle(List<Tag> tags) {
        Scanner in = new Scanner(System.in);
        System.out.print("\t\u29bf Title to Find : ");
        String title = in.nextLine();
        List<Tag> foundTags = tags.stream()
                .filter(tag -> tag.getTitle().contains(title))
                .collect(Collectors.toList());
        return foundTags;
    }

    public static void add(){
        Scanner in = new Scanner(System.in);
        TagRepository tagRepository = TagRepository.getInstance();

        Tag tagToAdd = new Tag();

        System.out.print("\t\u29bf Title : ");
        String title = in.nextLine();
        tagToAdd.setTitle(title);

        tagRepository.save(tagToAdd);

        Tag addedTag = tagRepository.findById(tagToAdd.getId());
        if (addedTag != null)
            System.out.println("\t\u2714 Tag successfully Added.\n");
        else
            System.out.println("\t\u274c Failed to Add Tag!\n");
    }

    public static void delete(Tag tagToDelete) {
        TagRepository tagRepository = TagRepository.getInstance();
        tagRepository.remove(tagToDelete);

        Tag deletedTag = tagRepository.findById(tagToDelete.getId());
        if (deletedTag == null)
            System.out.println("\t\u2714 Tag successfully Deleted.\n");
        else
            System.out.println("\t\u274c Failed to Delete Tag!\n");
    }
}
