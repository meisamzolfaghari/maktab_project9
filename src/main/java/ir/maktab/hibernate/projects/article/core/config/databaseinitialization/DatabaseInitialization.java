package ir.maktab.hibernate.projects.article.core.config.databaseinitialization;

import ir.maktab.hibernate.projects.article.core.AllRoles;
import ir.maktab.hibernate.projects.article.entities.Category;
import ir.maktab.hibernate.projects.article.entities.Role;
import ir.maktab.hibernate.projects.article.entities.Tag;
import ir.maktab.hibernate.projects.article.entities.User;
import ir.maktab.hibernate.projects.article.repositories.CategoryRepository;
import ir.maktab.hibernate.projects.article.repositories.RoleRepository;
import ir.maktab.hibernate.projects.article.repositories.TagRepository;
import ir.maktab.hibernate.projects.article.repositories.UserRepository;
import ir.maktab.hibernate.projects.article.userinterface.functions.Roles;
import ir.maktab.hibernate.projects.article.userinterface.functions.Users;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DatabaseInitialization {
    public static void execute() {
        roleInitialize();
        adminInitialize();
        categoryInitialize();
        tagInitialize();
    }

    public static void roleInitialize() {
        RoleRepository roleRepository = RoleRepository.getInstance();
        if (roleRepository.findAll().isEmpty()) {
            roleRepository.save(new Role(null, AllRoles.writer.name(), new ArrayList<>()));
            roleRepository.save(new Role(null, AllRoles.manager.name(), new ArrayList<>()));
            roleRepository.save(new Role(null, AllRoles.admin.name(), new ArrayList<>()));
        }
    }

    public static void adminInitialize() {
        UserRepository userRepository = UserRepository.getInstance();
        List<User> users = userRepository.findAll();
        if (users.isEmpty() || users.stream().noneMatch(Users::isAdmin))
            userRepository.save(new User(null, AllRoles.admin.name(), "123"
                    , new Date(System.currentTimeMillis()), "123", new ArrayList<>()
                    , new ArrayList<>(
                    Arrays.asList(
                            Roles.getAdminRole()
                            , Roles.getManagerRole()
                            , Roles.getWriterRole()))));
    }

    public static void categoryInitialize() {
        CategoryRepository categoryRepository = CategoryRepository.getInstance();
        if (categoryRepository.findAll().isEmpty()) {
            categoryRepository.save(new Category(null, "category1", "decription1", new ArrayList<>()));
            categoryRepository.save(new Category(null, "category2", "decription2", new ArrayList<>()));
            categoryRepository.save(new Category(null, "category3", "decription3", new ArrayList<>()));
            categoryRepository.save(new Category(null, "category4", "decription4", new ArrayList<>()));
            categoryRepository.save(new Category(null, "category5", "decription5", new ArrayList<>()));
        }
    }

    public static void tagInitialize() {
        TagRepository tagRepository = TagRepository.getInstance();
        if (tagRepository.findAll().isEmpty()) {
            tagRepository.save(new Tag(null, "tag1", new ArrayList<>()));
            tagRepository.save(new Tag(null, "tag2", new ArrayList<>()));
            tagRepository.save(new Tag(null, "tag3", new ArrayList<>()));
            tagRepository.save(new Tag(null, "tag4", new ArrayList<>()));
            tagRepository.save(new Tag(null, "tag5", new ArrayList<>()));
        }
    }

}
