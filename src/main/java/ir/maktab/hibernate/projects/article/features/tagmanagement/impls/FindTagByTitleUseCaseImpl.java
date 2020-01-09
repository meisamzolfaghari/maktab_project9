package ir.maktab.hibernate.projects.article.features.tagmanagement.impls;

import ir.maktab.hibernate.projects.article.entities.Tag;
import ir.maktab.hibernate.projects.article.features.tagmanagement.usecases.FindTagByTitleUseCase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FindTagByTitleUseCaseImpl implements FindTagByTitleUseCase {
    @Override
    public List<Tag> list(String title) {
        if (title == null || title.isEmpty()) {
            System.out.println("\t\u274c Failed to Find Tag! Title Error.\n");
            return new ArrayList<>();
        }

        List<Tag> tags = tagRepository.findAll();
        if (tags.isEmpty()) {
            System.out.println("\t\u274c Failed to Find Tag! There is no Tag.\n");
            return tags;
        }

        return tags.stream()
                .filter(tag -> tag.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }
}
