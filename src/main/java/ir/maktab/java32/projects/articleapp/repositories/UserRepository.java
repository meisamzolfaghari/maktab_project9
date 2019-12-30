package ir.maktab.java32.projects.articleapp.repositories;

import ir.maktab.java32.projects.articleapp.core.config.hibernate.repository.CrudRepository;
import ir.maktab.java32.projects.articleapp.entities.User;

public class UserRepository extends CrudRepository<User , Long> {
    private static UserRepository userRepository;

    private UserRepository() {
    }

    public UserRepository getInstance(){
        if (userRepository == null)
            userRepository = new UserRepository();
        return userRepository;
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}
