package ir.maktab.hibernate.projects.article.features.usermanagement.usecases;

import ir.maktab.hibernate.projects.article.entities.User;
import ir.maktab.hibernate.projects.article.features.usermanagement.UserUseCase;

public interface ChangePasswordUseCase extends UserUseCase {
    User change(String newPassword);
}
