package ir.maktab.java32.projects.articleapp.repositories;

import ir.maktab.java32.projects.articleapp.core.config.hibernate.repository.CrudRepository;
import ir.maktab.java32.projects.articleapp.entities.Tag;

public class TagRepository extends CrudRepository<Tag , Long> {
    private static TagRepository tagRepository;

    private TagRepository() {
    }

    public TagRepository getInstance(){
        if (tagRepository == null)
            tagRepository = new TagRepository();
        return tagRepository;
    }

    @Override
    protected Class<Tag> getEntityClass() {
        return Tag.class;
    }
}
