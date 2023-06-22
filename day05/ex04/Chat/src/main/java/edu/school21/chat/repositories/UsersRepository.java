package edu.school21.chat.repositories;

import edu.school21.chat.models.User;
import edu.school21.chat.exception.NotSavedSubEntityException;

import java.util.List;

public interface UsersRepository {
    List<User> findAll(int page, int size) throws NotSavedSubEntityException;
}
