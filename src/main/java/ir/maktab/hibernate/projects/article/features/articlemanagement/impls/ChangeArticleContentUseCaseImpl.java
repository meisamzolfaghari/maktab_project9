package ir.maktab.hibernate.projects.article.features.articlemanagement.impls;

import ir.maktab.hibernate.projects.article.entities.Article;
import ir.maktab.hibernate.projects.article.features.articlemanagement.usecases.ChangeArticleContentUseCase;

import java.util.Date;

public class ChangeArticleContentUseCaseImpl implements ChangeArticleContentUseCase {
    @Override
    public Article change(Article articleForEdit, String newContent , Date currentDate) {


        if (articleForEdit == null) return null;
        if (newContent == null || newContent.isEmpty()) return null;
        if (currentDate == null) return null;

        articleForEdit.setContent(newContent);
        articleForEdit.setLastUpdateDate(currentDate);

        articleRepository.update(articleForEdit);

        Article editedArticle = articleRepository.findById(articleForEdit.getId());

        if (editedArticle.getContent().equals(articleForEdit.getContent())
                && editedArticle.getLastUpdateDate().equals(currentDate))
            System.out.println("\t\u2714 Article's Content successfully Edited.\n");
        else
            System.out.println("\t\u274c Failed to Edit Articles Content!\n");

        return editedArticle;
    }
}
