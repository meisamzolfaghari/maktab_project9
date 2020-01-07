package ir.maktab.hibernate.projects.article.features.articlemanagement.impls;

import ir.maktab.hibernate.projects.article.entities.Article;
import ir.maktab.hibernate.projects.article.features.articlemanagement.usecases.UnPublishArticleUseCase;

import java.util.Date;

public class UnPublishArticleUseCaseImpl implements UnPublishArticleUseCase {
    @Override
    public Article unPublish(Article articleForPublish, Date currentDate) {
        if (articleForPublish == null) return null;
        if (currentDate == null) return null;

        if (articleForPublish.isPublished()) {
            System.out.println("\t\u274c UnPublish failed! This Article is Already Unpublished.\n");
            return null;
        }

        Article unpublishedArticle;

        articleForPublish.setPublished(false);
        articleForPublish.setPublishDate(null);

        articleRepository.update(articleForPublish);
        unpublishedArticle = articleRepository.findById(articleForPublish.getId());

        if (!unpublishedArticle.isPublished() && unpublishedArticle.getPublishDate().equals(currentDate))
            System.out.println("\t\u2714 Article successfully Unpublished.\n");
        else
            System.out.println("\t\u274c UnPublish failed!\n");

        return unpublishedArticle;
    }
}
