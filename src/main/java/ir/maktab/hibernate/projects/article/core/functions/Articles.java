package ir.maktab.hibernate.projects.article.core.functions;

import ir.maktab.hibernate.projects.article.core.menus.CategoryMenu;
import ir.maktab.hibernate.projects.article.core.share.AuthenticationService;
import ir.maktab.hibernate.projects.article.entities.Article;
import ir.maktab.hibernate.projects.article.repositories.ArticleRepository;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Articles {

    public static Article choose(List<Article> articles) {

        if (articles.isEmpty()) return null;

        Scanner in = new Scanner(System.in);

        Long id;

        do {
            System.out.print("\t\u29bf ID : ");
            id = in.nextLong();
            for (Article article : articles)
                if (id.equals(article.getId()))
                    return article;

            System.out.println("\t\u26a0 Invalid ID! Try again...\n");

        } while (true);
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
                        + "' , Title= '"+ article.getTitle()
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
                        + "'\n    Title= '"+ article.getTitle()
                        + "'\n    Brief= '" + article.getBrief()
                        + "'\n    Content= '" + article.getContent()
                        + "'\n    User= '" + article.getUser().getUsername()
                        + "'\n    Tags= '" + article.getTags()
                        + "'\n------------------------------------------------------------------------------------");
    }

    public static void display(Article article) {

        if (article == null) return;

        String publishStatus = (article.isPublished())? "Published" : "Not Published";
        System.out.println(
                ""
                        + "------------------------------------------------------------------------------------"
                        + "\n    ID= " + article.getId()
                        + "\n    Category= '" + article.getCategory().getTitle()
                        + "'\n    Title= '"+ article.getTitle()
                        + "'\n    Brief= '" + article.getBrief()
                        + "'\n    Content= '" + article.getContent()
                        + "'\n    Create Date= " + article.getCreateDate()
                        + "\n    Last Update Date= " + article.getLastUpdateDate()
                        + "\n    Publish Date= " + article.getPublishDate()
                        + "\n    Publish Status= '" + publishStatus
                        + "'\n    User= '" + article.getUser().getUsername()
                        + "'\n------------------------------------------------------------------------------------");
    }

    public static void publish(Article article, Date currentDate) {

        if (article == null || currentDate == null) return;

        if (article.isPublished()) {

            ArticleRepository articleRepository = ArticleRepository.getInstance();
            Article editedArticle;

            article.setPublished(true);
            article.setPublishDate(currentDate);

            articleRepository.update(article);
            editedArticle = articleRepository.findById(article.getId());

            if (editedArticle.isPublished() && editedArticle.getPublishDate().equals(currentDate))
                System.out.println("\t\u2714 Article successfully published.\n");
            else
                System.out.println("\t\u274c publish failed!\n");
        } else
            System.out.println("\t\u274c publish failed! you have Already published this Article.\n");
    }

    public static void unpublish(Article article, Date currentDate) {

        if (article == null || currentDate == null) return;

        if (!article.isPublished()) {

            ArticleRepository articleRepository = ArticleRepository.getInstance();
            Article editedArticle;

            article.setPublished(false);
            article.setPublishDate(null);

            articleRepository.update(article);
            editedArticle = articleRepository.findById(article.getId());

            if (!editedArticle.isPublished() && editedArticle.getPublishDate().equals(currentDate))
                System.out.println("\t\u2714 Article successfully Unpublished.\n");
            else
                System.out.println("\t\u274c Unpublish Failed!\n");
        } else
            System.out.println("\t\u274c Unpublish failed! you have Already Unpublished this Article.\n");
    }

    public static void delete(Article article) {
        if (article == null) return;

        ArticleRepository articleRepository = ArticleRepository.getInstance();

        articleRepository.remove(article);
        if (articleRepository.findById(article.getId()) == null)
            System.out.println("\t\u2714 Article successfully Deleted.\n");
        else
            System.out.println("\t\u274c Delete Article Failed!\n");
    }

    public static Article add(){
        Scanner in = new Scanner(System.in);

        CategoryMenu categoryMenu = new CategoryMenu();

        Article article = new Article();
        Date currentTime = new Date(System.currentTimeMillis());

        categoryMenu.execute();

        article.setCategory(categoryMenu.getChosenCategory());

        System.out.print("\t\u29bf Title : ");
        article.setTitle(in.nextLine());

        System.out.print("\t\u29bf Brief : ");
        article.setBrief(in.nextLine());

        System.out.print("\t\u29bf Content : ");
        article.setContent(in.nextLine());

        article.setCreateDate(currentTime);
        article.setLastUpdateDate(null);

        article.setPublished(false);
        article.setPublishDate(null);

        System.out.print("\t\u29bf do yo want to publish?(y for Yes and anything for No): ");
        if (in.next().charAt(0) == 'y') {
            article.setPublished(true);
            article.setPublishDate(currentTime);
        }

        article.setUser(AuthenticationService.getInstance().getLoginUser());

        return article;
    }


}