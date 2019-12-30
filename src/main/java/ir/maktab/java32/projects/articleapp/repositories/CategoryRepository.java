package ir.maktab.java32.projects.articleapp.repositories;

import ir.maktab.java32.projects.articleapp.core.config.hibernate.repository.CrudRepository;
import ir.maktab.java32.projects.articleapp.entities.Category;

public class CategoryRepository extends CrudRepository<Category , Long> {

    private static CategoryRepository categoryRepository;

    private CategoryRepository() {
    }

    public CategoryRepository getInstance(){
        if (categoryRepository == null)
            categoryRepository = new CategoryRepository();
        return categoryRepository;
    }

    @Override
    protected Class<Category> getEntityClass() {
        return Category.class;
    }
}
