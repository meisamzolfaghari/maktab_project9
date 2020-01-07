package ir.maktab.hibernate.projects.article.core.config.role;

import ir.maktab.hibernate.projects.article.core.AllRoles;
import ir.maktab.hibernate.projects.article.entities.Role;
import ir.maktab.hibernate.projects.article.repositories.RoleRepository;

import java.util.ArrayList;

public class RoleConfig {
    public static void config() {
        RoleRepository roleRepository = RoleRepository.getInstance();
        if (roleRepository.findAll().isEmpty()) {
            roleRepository.save(new Role(null, AllRoles.writer.name(), new ArrayList<>()));
            roleRepository.save(new Role(null, AllRoles.manager.name(), new ArrayList<>()));
            roleRepository.save(new Role(null, AllRoles.admin.name(), new ArrayList<>()));
        }
    }
}
