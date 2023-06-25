package school21.spring.service.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        UsersRepository usersRepository = context.getBean("usersRepositoryJdbcImpl", UsersRepositoryJdbcImpl.class);
        createTable(usersRepository);

        for (Long i = 1L; i < 11L; i++)
            usersRepository.delete(i);
        System.out.println();

        usersRepository = context.getBean("usersRepositoryJdbcTemplateImpl", UsersRepositoryJdbcTemplateImpl.class);
        createTable(usersRepository);
    }

    public static void createTable(UsersRepository usersRepository) {
        User[] users = {
                new User(1L, "random@gmail.com"),
                new User(2L, "random1@gmail.com"),
                new User(3L, "random2@gmail.com"),
                new User(4L, "random3@gmail.com"),
                new User(5L, "random4@gmail.com"),
                new User(6L, "random5gmail.com"),
                new User(7L, "random6@gmail.com"),
                new User(8L, "random7@gmail.com"),
                new User(9L, "random8@gmail.com"),
                new User(10L, "random9@gmail.com"),
        };

        for (User insert : users) {
            usersRepository.save(insert);
        }

        for (User usersInDb : usersRepository.findAll()) {
            System.out.println(usersInDb);
        }

        usersRepository.update(new User(3L, "newEmail@gmail.com"));
        System.out.println(usersRepository.findById(3L));

        usersRepository.delete(3L);
    }
}
