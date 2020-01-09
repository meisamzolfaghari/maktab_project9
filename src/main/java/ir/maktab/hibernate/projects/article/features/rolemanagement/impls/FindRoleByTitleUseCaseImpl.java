package ir.maktab.hibernate.projects.article.features.rolemanagement.impls;

import ir.maktab.hibernate.projects.article.entities.Role;
import ir.maktab.hibernate.projects.article.features.rolemanagement.usecases.FindRoleByTitleUseCase;

import java.util.List;

public class FindRoleByTitleUseCaseImpl implements FindRoleByTitleUseCase {
    @Override
    public Role find(String title) {
        if (title == null || title.isEmpty()) {
            System.out.println("\t\u274c Failed to Find Role! Title Error.\n");
            return new Role();
        }
        List<Role> roles = roleRepository.findAll();
        if (roles.isEmpty()) {
            System.out.println("\t\u274c Failed to Find Role! There is no Roles.\n");
            return new Role();
        }

        return roles
                .stream()
                .filter(role -> role.getTitle().equals(title))
                .findFirst().orElse(new Role());
    }
}
