package ir.maktab.hibernate.projects.article.features.usermanagement;

import ir.maktab.hibernate.projects.article.repositories.UserRepository;

public interface UserUseCase {
    UserRepository userRepository = UserRepository.getInstance();
}
