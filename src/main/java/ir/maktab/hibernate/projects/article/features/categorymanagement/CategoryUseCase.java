package ir.maktab.hibernate.projects.article.features.categorymanagement;

import ir.maktab.hibernate.projects.article.repositories.CategoryRepository;

public interface CategoryUseCase {
    CategoryRepository categoryRepository = CategoryRepository.getInstance();
}
