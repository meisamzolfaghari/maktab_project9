package ir.maktab.hibernate.projects.article.repositories;

import ir.maktab.hibernate.projects.article.core.config.hibernate.repository.CrudRepository;
import ir.maktab.hibernate.projects.article.entities.Tag;

public class TagRepository extends CrudRepository<Tag , Long> {
    private static TagRepository tagRepository;

    private TagRepository() {
    }

    public static TagRepository getInstance(){
        if (tagRepository == null)
            tagRepository = new TagRepository();
        return tagRepository;
    }

    @Override
    protected Class<Tag> getEntityClass() {
        return Tag.class;
    }
}
