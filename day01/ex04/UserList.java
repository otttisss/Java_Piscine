package day01.ex04;

public interface UserList {
    void addUser(User user);
    User getUserId(int id) throws UserNotFoundException;
    User getUserIndex(int index) throws UserNotFoundException;
    int getListSize();
}
