package ir.maktab.hibernate.projects.article.features.categorymanagement.impls;

import ir.maktab.hibernate.projects.article.entities.Category;
import ir.maktab.hibernate.projects.article.features.categorymanagement.usecases.AddCategoryUseCase;

import java.util.ArrayList;

public class AddCategoryUseCaseImpl implements AddCategoryUseCase {
    @Override
    public Category add(String title, String description) {
        if (title == null || title.isEmpty()) {
            System.out.println("\t\u274c Failed to Add Category! Title Error.\n");
            return null;
        }
        if (description == null || description.isEmpty()) {
            System.out.println("\t\u274c Failed to Add Category! Description Error.\n");
            return null;
        }

        if (categoryRepository.findAll().stream().anyMatch(category -> category.getTitle().equals(title)
                && category.getDescription().equals(description))) {
            System.out.println("\t\u274c Failed to Add Category! This Category Already Exist.\n");
            return null;
        }
            Category categoryToAdd = new Category(null , title, description , new ArrayList<>());

        categoryToAdd.setId(categoryRepository.save(categoryToAdd));
        Category addedCategory = categoryRepository.findById(categoryToAdd.getId());
        if (addedCategory != null)
            System.out.println("\t\u2714 Category successfully Added.\n");
        else
            System.out.println("\t\u274c Failed to Add Category!\n");
        return addedCategory;
    }
}
