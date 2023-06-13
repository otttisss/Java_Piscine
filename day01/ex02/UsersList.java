package day01.ex02;

public interface UsersList {
    void addUser(User user);
    User getUserId(int id) throws UserNotFoundException;
    User getUserIndex(int index) throws UserNotFoundException;
    int getListSize();
}
