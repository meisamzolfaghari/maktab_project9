package ir.maktab.hibernate.projects.article.repositories;

import ir.maktab.hibernate.projects.article.core.config.hibernate.repository.CrudRepository;
import ir.maktab.hibernate.projects.article.entities.User;

public class UserRepository extends CrudRepository<User , Long> {
    private static UserRepository userRepository;

    private UserRepository() {
    }

    public static UserRepository getInstance(){
        if (userRepository == null)
            userRepository = new UserRepository();
        return userRepository;
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}
