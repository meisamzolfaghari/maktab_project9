package ir.maktab.java32.projects.articleapp.repositories;

import ir.maktab.java32.projects.articleapp.core.config.hibernate.repository.CrudRepository;
import ir.maktab.java32.projects.articleapp.entities.Role;


public class RoleRepository extends CrudRepository<Role, Long> {
    private static RoleRepository roleRepository;

    private RoleRepository() {
    }

    public RoleRepository getInstance(){
        if (roleRepository == null)
            roleRepository = new RoleRepository();
        return roleRepository;
    }

    @Override
    protected Class<Role> getEntityClass() {
        return Role.class;
    }
}
