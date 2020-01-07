package ir.maktab.hibernate.projects.article.features.usermanagement.usecases;

import ir.maktab.hibernate.projects.article.entities.User;
import ir.maktab.hibernate.projects.article.features.usermanagement.UserUseCase;

import java.util.Date;

public interface RegisterUseCase extends UserUseCase {
    User register(String username , String nationalCode , Date birthday);
}
