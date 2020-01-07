package ir.maktab.hibernate.projects.article.features.rolemanagement;

import ir.maktab.hibernate.projects.article.repositories.RoleRepository;

public interface RoleUseCase {
    RoleRepository roleRepository = RoleRepository.getInstance();
}
