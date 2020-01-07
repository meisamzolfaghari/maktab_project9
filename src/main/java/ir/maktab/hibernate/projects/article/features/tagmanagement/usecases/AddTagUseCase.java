package ir.maktab.hibernate.projects.article.features.tagmanagement.usecases;

import ir.maktab.hibernate.projects.article.entities.Tag;
import ir.maktab.hibernate.projects.article.features.tagmanagement.TagUseCase;

public interface AddTagUseCase extends TagUseCase {
    Tag add(String title);
}
