package ir.maktab.hibernate.projects.article.features.tagmanagement.impls;

import ir.maktab.hibernate.projects.article.entities.Tag;
import ir.maktab.hibernate.projects.article.features.tagmanagement.usecases.FindAllTagUseCase;

import java.util.List;

public class FindAllTagUseCaseImpl implements FindAllTagUseCase {
    @Override
    public List<Tag> list() {
        return tagRepository.findAll();
    }
}
