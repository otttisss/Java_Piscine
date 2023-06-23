package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import javax.persistence.EntityNotFoundException;
public class UserServiceImpl {
    private final UsersRepository usersRepository;

    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public boolean authenticate(String login, String password) throws AlreadyAuthenticatedException, EntityNotFoundException {
        User user = usersRepository.findByLogin(login);

        if (user == null)
            throw new EntityNotFoundException();
        if (user.isAuthenticated())
            throw new AlreadyAuthenticatedException("Authentication doesn't exist");
        if (user.getPassword().equals(password)) {
            user.setAuthenticated(true);
            usersRepository.update(user);
            return true;
        }
        return false;
    }
}
