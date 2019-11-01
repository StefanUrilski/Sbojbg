package app.repository;

import app.domain.entities.User;

public interface UserRepository extends GenericRepository<User, String> {

    User exists(String username, String password);

    User findByUsername(String username);
}
