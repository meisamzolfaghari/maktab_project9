package ir.maktab.hibernate.projects.article.features.articlemanagement.impls;

import ir.maktab.hibernate.projects.article.entities.Article;
import ir.maktab.hibernate.projects.article.features.articlemanagement.usecases.ChangeArticleBriefUseCase;

import java.util.Date;

public class ChangeArticleBriefUseCaseImpl implements ChangeArticleBriefUseCase {
    @Override
    public Article change(Article articleForEdit, String newBrief , Date currentDate) {

        if (articleForEdit == null) return null;
        if (newBrief == null || newBrief.isEmpty()) return null;
        if (currentDate == null) return null;

        articleForEdit.setBrief(newBrief);
        articleForEdit.setLastUpdateDate(currentDate);

        articleRepository.update(articleForEdit);

        Article editedArticle = articleRepository.findById(articleForEdit.getId());

        if (editedArticle.getBrief().equals(articleForEdit.getBrief())
                && editedArticle.getLastUpdateDate().equals(currentDate))
            System.out.println("\t\u2714 Article's Brief successfully Edited.\n");
        else
            System.out.println("\t\u274c Failed to Edit Articles Brief!\n");

        return editedArticle;
    }
}
