package ir.maktab.hibernate.projects.article.features.rolemanagement.usecases;

import ir.maktab.hibernate.projects.article.entities.Role;
import ir.maktab.hibernate.projects.article.features.rolemanagement.RoleUseCase;

import java.util.List;

public interface FindAllRoleUseCase extends RoleUseCase {
    List<Role> list();
}
