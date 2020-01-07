package ir.maktab.hibernate.projects.article.features.tagmanagement.usecases;

import ir.maktab.hibernate.projects.article.entities.Tag;
import ir.maktab.hibernate.projects.article.features.tagmanagement.TagUseCase;

import java.util.List;

public interface FindTagByTitleUseCase extends TagUseCase {
    List<Tag> list(String title);
}
