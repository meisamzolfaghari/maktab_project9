package ir.maktab.hibernate.projects.article.features.tagmanagement.usecases;

import ir.maktab.hibernate.projects.article.entities.Tag;
import ir.maktab.hibernate.projects.article.features.tagmanagement.TagUseCase;

public interface DeleteTagUseCase extends TagUseCase {
    Boolean delete(Tag tag);
}
