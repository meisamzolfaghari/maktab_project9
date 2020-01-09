package ir.maktab.hibernate.projects.article;

import ir.maktab.hibernate.projects.article.core.config.admin.AdminConfig;
import ir.maktab.hibernate.projects.article.core.config.category.CategoryConfig;
import ir.maktab.hibernate.projects.article.core.config.role.RoleConfig;
import ir.maktab.hibernate.projects.article.core.config.tag.TagConfig;
import ir.maktab.hibernate.projects.article.userinterface.menus.FirstMenu;

public class Application {

    public static void main(String[] args) {
        RoleConfig.config();
        AdminConfig.config();
        TagConfig.config();
        CategoryConfig.config();
        new FirstMenu().execute();
    }
}
