package day01.ex02;

public class UserIdsGenerator {
    private static final UserIdsGenerator instance = new UserIdsGenerator();
    private static Integer id = 0;

    public static UserIdsGenerator getInstance() {
        return instance;
    }

    public static int generatedId() {
        id++;

        return id;
    }

    public static Integer getId() {
        return id;
    }
}
