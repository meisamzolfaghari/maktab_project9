package ir.maktab.hibernate.projects.article.repositories;

import ir.maktab.hibernate.projects.article.core.config.hibernate.repository.CrudRepository;
import ir.maktab.hibernate.projects.article.entities.Role;


public class RoleRepository extends CrudRepository<Role, Long> {
    private static RoleRepository roleRepository;

    private RoleRepository() {
    }

    public static RoleRepository getInstance(){
        if (roleRepository == null)
            roleRepository = new RoleRepository();
        return roleRepository;
    }

    @Override
    protected Class<Role> getEntityClass() {
        return Role.class;
    }
}
