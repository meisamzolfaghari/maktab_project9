package ir.maktab.hibernate.projects.article.repositories;

import ir.maktab.hibernate.projects.article.core.config.hibernate.repository.CrudRepository;
import ir.maktab.hibernate.projects.article.entities.Category;

public class CategoryRepository extends CrudRepository<Category , Long> {

    private static CategoryRepository categoryRepository;

    private CategoryRepository() {
    }

    public static CategoryRepository getInstance(){
        if (categoryRepository == null)
            categoryRepository = new CategoryRepository();
        return categoryRepository;
    }

    @Override
    protected Class<Category> getEntityClass() {
        return Category.class;
    }
}
