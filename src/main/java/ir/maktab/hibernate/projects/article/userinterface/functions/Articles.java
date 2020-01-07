package ir.maktab.hibernate.projects.article.userinterface.functions;

import ir.maktab.hibernate.projects.article.entities.Article;
import ir.maktab.hibernate.projects.article.entities.Category;
import ir.maktab.hibernate.projects.article.entities.Tag;
import ir.maktab.hibernate.projects.article.features.categorymanagement.impls.FindAllCategoryUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.categorymanagement.usecases.FindAllCategoryUseCase;
import ir.maktab.hibernate.projects.article.features.tagmanagement.impls.FindAllTagUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.tagmanagement.usecases.FindAllTagUseCase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Articles {
    private static Scanner in = new Scanner(System.in);

    public static Article choose(List<Article> articles) {

        if (articles.isEmpty()) return null;

        Long id;

        System.out.print("\t\u29bf ID : ");
        id = in.nextLong();

        for (Article article : articles)
            if (id.equals(article.getId()))
                return article;

        System.out.println("\t\u26a0 Invalid ID!\n");
        return null;

    }

    public static void displayAll(List<Article> articles) {

        if (articles.isEmpty()) return;

        System.out.println("------------------------------------------------------------------------------------" +
                "\n                              Articles");
        articles.forEach(article -> System.out.println(
                ""
                        + "------------------------------------------------------------------------------------"
                        + "\nID= " + article.getId()
                        + " , Category= '" + article.getCategory().getTitle()
                        + "' , Title= '" + article.getTitle()
                        + "' , Brief= '" + article.getBrief()
                        + "'\n------------------------------------------------------------------------------------"));
    }

    public static void displayShortVersion(Article article) {

        if (article == null) return;

        System.out.println(
                ""
                        + "------------------------------------------------------------------------------------"
                        + "\n    ID= " + article.getId()
                        + "\n    Category= '" + article.getCategory().getTitle()
                        + "'\n    Title= '" + article.getTitle()
                        + "'\n    Brief= '" + article.getBrief()
                        + "'\n    Content= '" + article.getContent()
                        + "'\n    User= '" + article.getUser().getUsername()
                        + "'\n    Tags= '" + article.getTags()
                        + "'\n------------------------------------------------------------------------------------");
    }

    public static void displayFullVersion(Article article) {

        if (article == null) return;

        String publishStatus = (article.isPublished()) ? "Published" : "Not Published";
        System.out.println(
                ""
                        + "------------------------------------------------------------------------------------"
                        + "\n    ID= " + article.getId()
                        + "\n    Category= '" + article.getCategory().getTitle()
                        + "'\n    Title= '" + article.getTitle()
                        + "'\n    Brief= '" + article.getBrief()
                        + "'\n    Content= '" + article.getContent()
                        + "'\n    Create Date= " + article.getCreateDate()
                        + "\n    Last Update Date= " + article.getLastUpdateDate()
                        + "\n    Publish Date= " + article.getPublishDate()
                        + "\n    Publish Status= '" + publishStatus
                        + "'\n    User= '" + article.getUser().getUsername()
                        + "'\n------------------------------------------------------------------------------------");
    }

    public static String takeTitle() {
        System.out.print("\t\u29bf Title >>> ");
        String title = in.nextLine();

        return title;
    }

    public static String takeBrief() {
        System.out.print("\t\u29bf Brief >>> ");
        String brief = in.nextLine();

        return brief;
    }

    public static String takeContent() {
        System.out.print("\t\u29bf Content >>> ");
        String content = in.nextLine();

        return content;
    }

    public static Date takeCurrentTime() {
        return new Date(System.currentTimeMillis());
    }

    public static Boolean takePublishStatus() {
        System.out.print("\t\u29bf do yo want to publish?(y for Yes and anything for No): ");
        if (in.next().charAt(0) == 'y')
            return true;
        return false;
    }

    public static Category takeCategory() {
        FindAllCategoryUseCase findAllCategoryUseCase
                = new FindAllCategoryUseCaseImpl();
        List<Category> categories = findAllCategoryUseCase.list();
        if (categories.isEmpty()) return null;

        Categories.displayAll(categories);

        Category chosenCategory = null;
        while (chosenCategory == null)
            chosenCategory = Categories.choose(categories);

        return chosenCategory;
    }

    public static List<Tag> takeTags() {
        FindAllTagUseCase findAllTagUseCase = new FindAllTagUseCaseImpl();
        List<Tag> tags = findAllTagUseCase.list();
        if (tags.isEmpty()) return null;
        Tag chosenTag;
        List<Tag> chosenTags = new ArrayList<>();
        while (true) {
            Tags.displayAll(tags);
            chosenTag = Tags.choose(tags);
            if (chosenTag != null)
                chosenTags.add(chosenTag);
            else continue;
            System.out.println("\t\u29bf Do You Want to Add more Tags(y for yes and anything else for No): ");
            if (in.next().charAt(0) != 'y')
                break;
        }

        return chosenTags;
    }

    public static String takeNewTitle() {
        System.out.print("\t\u29bf New Title >>> ");
        String newTitle = in.nextLine();

        return newTitle;
    }

    public static String takeNewBrief() {
        System.out.print("\t\u29bf New Brief >>> ");
        String newBrief = in.nextLine();

        return newBrief;
    }

    public static String takeNewContent() {
        System.out.print("\t\u29bf New Content >>> ");
        String newContent = in.nextLine();

        return newContent;
    }
}