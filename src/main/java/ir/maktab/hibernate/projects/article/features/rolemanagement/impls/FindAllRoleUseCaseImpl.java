package ir.maktab.hibernate.projects.article.features.rolemanagement.impls;

import ir.maktab.hibernate.projects.article.entities.Role;
import ir.maktab.hibernate.projects.article.features.rolemanagement.usecases.FindAllRoleUseCase;

import java.util.List;

public class FindAllRoleUseCaseImpl implements FindAllRoleUseCase {
    @Override
    public List<Role> list() {
        return roleRepository.findAll();
    }
}
