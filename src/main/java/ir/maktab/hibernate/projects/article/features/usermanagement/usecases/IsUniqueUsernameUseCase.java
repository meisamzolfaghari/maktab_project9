package ir.maktab.hibernate.projects.article.features.usermanagement.usecases;

import ir.maktab.hibernate.projects.article.features.usermanagement.UserUseCase;

public interface IsUniqueUsernameUseCase extends UserUseCase {
    Boolean test(String username);
}
