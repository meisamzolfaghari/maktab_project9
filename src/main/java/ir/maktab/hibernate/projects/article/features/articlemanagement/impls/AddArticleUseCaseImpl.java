package ir.maktab.hibernate.projects.article.features.articlemanagement.impls;

import ir.maktab.hibernate.projects.article.entities.Article;
import ir.maktab.hibernate.projects.article.entities.Category;
import ir.maktab.hibernate.projects.article.entities.Tag;
import ir.maktab.hibernate.projects.article.entities.User;
import ir.maktab.hibernate.projects.article.features.articlemanagement.usecases.AddArticleUseCase;

import java.util.Date;
import java.util.List;

public class AddArticleUseCaseImpl implements AddArticleUseCase {
    @Override
    public Article add(String title, String brief, String content, Date currentDate
            , User user, Category category, List<Tag> tags) {
        if (title == null || title.isEmpty()) {
            System.out.println("\t\u274c Failed to Add Article!\n");
            return null;
        }
        if (brief == null || brief.isEmpty()){
            System.out.println("\t\u274c Failed to Add Article!\n");
            return null;
        }
        if (content == null || content.isEmpty()){
            System.out.println("\t\u274c Failed to Add Article!\n");
            return null;
        }
        if (currentDate == null){
            System.out.println("\t\u274c Failed to Add Article!\n");
            return null;
        }
        if (user == null){
            System.out.println("\t\u274c Failed to Add Article!\n");
            return null;
        }
        if (category == null){
            System.out.println("\t\u274c Failed to Add Article!\n");
            return null;
        }
        if (tags == null || tags.isEmpty()){
            System.out.println("\t\u274c Failed to Add Article!\n");
            return null;
        }

        Article articleToAdd = new Article(title , brief , content , currentDate
                , false, user, category, tags);
        articleToAdd.setId(articleRepository.save(articleToAdd));

        Article addedArticle = articleRepository.findById(articleToAdd.getId());

        if (addedArticle != null)
            System.out.println("\t\u2714 Article successfully Added.\n");
        else
            System.out.println("\t\u274c Failed to Add Article!\n");

        return addedArticle;
    }

}
