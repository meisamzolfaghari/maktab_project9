package ir.maktab.hibernate.projects.article.features.usermanagement.usecases;

import ir.maktab.hibernate.projects.article.entities.User;
import ir.maktab.hibernate.projects.article.features.usermanagement.UserUseCase;

public interface ChangeNationalCodeUseCase extends UserUseCase {
    User change(String newNationalCode);
}
