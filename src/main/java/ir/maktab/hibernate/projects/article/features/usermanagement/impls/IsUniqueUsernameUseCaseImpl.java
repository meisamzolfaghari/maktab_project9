package ir.maktab.hibernate.projects.article.features.usermanagement.impls;

import ir.maktab.hibernate.projects.article.entities.User;
import ir.maktab.hibernate.projects.article.features.usermanagement.usecases.IsUniqueUsernameUseCase;

public class IsUniqueUsernameUseCaseImpl implements IsUniqueUsernameUseCase {
    @Override
    public Boolean test(String username) {
        if (username == null || username.isEmpty()) return null;

        for (User user : userRepository.findAll())
            if (user.getUsername().equals(username))
                return false;
        return true;
    }
}
