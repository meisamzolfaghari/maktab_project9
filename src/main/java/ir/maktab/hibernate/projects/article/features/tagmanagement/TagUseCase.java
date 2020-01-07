package ir.maktab.hibernate.projects.article.features.tagmanagement;

import ir.maktab.hibernate.projects.article.repositories.TagRepository;

public interface TagUseCase {
    TagRepository tagRepository = TagRepository.getInstance();
}
