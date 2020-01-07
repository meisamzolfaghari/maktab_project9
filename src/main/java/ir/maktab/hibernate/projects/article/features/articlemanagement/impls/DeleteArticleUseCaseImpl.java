package ir.maktab.hibernate.projects.article.features.articlemanagement.impls;

import ir.maktab.hibernate.projects.article.entities.Article;
import ir.maktab.hibernate.projects.article.features.articlemanagement.usecases.DeleteArticleUseCase;

public class DeleteArticleUseCaseImpl implements DeleteArticleUseCase {
    @Override
    public Boolean delete(Article articleForDelete) {
        if (articleForDelete == null) return false;

        articleRepository.remove(articleForDelete);
        if (articleRepository.findById(articleForDelete.getId()) == null) {
            System.out.println("\t\u2714 Article successfully Deleted.\n");
            return true;
        } else {
            System.out.println("\t\u274c Delete Article Failed!\n");
            return false;
        }
    }
}
