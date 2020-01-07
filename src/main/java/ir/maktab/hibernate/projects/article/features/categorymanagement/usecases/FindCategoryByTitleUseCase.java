package ir.maktab.hibernate.projects.article.features.categorymanagement.usecases;

import ir.maktab.hibernate.projects.article.entities.Category;
import ir.maktab.hibernate.projects.article.features.categorymanagement.CategoryUseCase;

import java.util.List;

public interface FindCategoryByTitleUseCase extends CategoryUseCase {
    List<Category> list(String title);
}
