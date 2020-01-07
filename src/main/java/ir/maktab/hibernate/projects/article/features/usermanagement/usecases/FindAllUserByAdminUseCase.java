package ir.maktab.hibernate.projects.article.features.usermanagement.usecases;

import ir.maktab.hibernate.projects.article.entities.User;
import ir.maktab.hibernate.projects.article.features.usermanagement.UserUseCase;

import java.util.List;

public interface FindAllUserByAdminUseCase extends UserUseCase {
    List<User> list();
}
