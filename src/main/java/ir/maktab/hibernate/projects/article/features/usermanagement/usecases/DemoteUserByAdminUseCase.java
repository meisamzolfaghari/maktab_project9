package ir.maktab.hibernate.projects.article.features.usermanagement.usecases;

import ir.maktab.hibernate.projects.article.entities.User;
import ir.maktab.hibernate.projects.article.features.usermanagement.UserUseCase;

public interface DemoteUserByAdminUseCase extends UserUseCase {
    Boolean demote(User user);
}
