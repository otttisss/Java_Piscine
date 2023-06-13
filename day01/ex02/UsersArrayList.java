package day01.ex02;

public class UsersArrayList implements UsersList {

    private int arrayCapacity = 10;
    private User[] users = new User[arrayCapacity];
    private int count = 0;

    @Override
    public void addUser(User user) {
        if (this.count == this.arrayCapacity) {
            User[] addUsers = new User[this.arrayCapacity * 2];

            for (int i = 0; i < this.arrayCapacity; i++) {
                addUsers[i] = this.users[i];
            }
            this.arrayCapacity = this.arrayCapacity * 2;
            this.users = addUsers;
            this.users[count++] = user;
        } else {
            this.users[count++] = user;
        }
    }

    @Override
    public User getUserId(int id) throws UserNotFoundException {
        for (int i = 0; i < this.count; i++) {
            if (users[i].getIdentifier() == id) {
                return users[i];
            }
        }
        throw new UserNotFoundException();
    }

    @Override
    public User getUserIndex(int index) throws UserNotFoundException {
        if (index < this.count && index >= 0)
            return users[index];
        throw new UserNotFoundException();
    }

    @Override
    public int getListSize() {
        return this.count;
    }

    public void printUsersInfo() {
        for (int i = 0; i < this.count; i++) {
            System.out.print(i + "\tName: " + users[i].getName() + " balance: " + users[i].getBalance());
            System.out.println("\tId: " + users[i].getIdentifier());
        }
    }
}
