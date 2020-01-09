package ir.maktab.hibernate.projects.article.core.config.category;

import ir.maktab.hibernate.projects.article.entities.Category;
import ir.maktab.hibernate.projects.article.repositories.CategoryRepository;

import java.util.ArrayList;

public class CategoryConfig {
    public static void config() {
        CategoryRepository categoryRepository = CategoryRepository.getInstance();
        if(categoryRepository.findAll().isEmpty()) {
            categoryRepository.save(new Category(null , "category1" , "decription1" , new ArrayList<>()));
            categoryRepository.save(new Category(null , "category2" , "decription2" , new ArrayList<>()));
            categoryRepository.save(new Category(null , "category3" , "decription3" , new ArrayList<>()));
            categoryRepository.save(new Category(null , "category4" , "decription4" , new ArrayList<>()));
            categoryRepository.save(new Category(null , "category5" , "decription5" , new ArrayList<>()));
        }
    }
}
