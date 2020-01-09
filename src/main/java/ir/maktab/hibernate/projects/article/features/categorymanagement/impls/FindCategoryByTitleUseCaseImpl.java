package ir.maktab.hibernate.projects.article.features.categorymanagement.impls;

import ir.maktab.hibernate.projects.article.entities.Category;
import ir.maktab.hibernate.projects.article.features.categorymanagement.usecases.FindAllCategoryUseCase;
import ir.maktab.hibernate.projects.article.features.categorymanagement.usecases.FindCategoryByTitleUseCase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FindCategoryByTitleUseCaseImpl implements FindCategoryByTitleUseCase {
    @Override
    public List<Category> list(String title) {
        if (title == null || title.isEmpty()) {
            System.out.println("\t\u274c Failed to Find Category! Title Error.\n");
            return new ArrayList<>();
        }

        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) return categories;

        return categories.stream()
                .filter(category -> category.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }
}
