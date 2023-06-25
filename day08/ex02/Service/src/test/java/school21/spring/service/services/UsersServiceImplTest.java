package school21.spring.service.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import school21.spring.service.config.TestApplicationConfig;

@Component
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsersServiceImplTest {
    private UsersService usersService;

    @BeforeEach
    void init() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestApplicationConfig.class);
        usersService = applicationContext.getBean("usersServiceJdbcTemplate", UsersServiceImpl.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"email1@gmail.com", "email2@gmail.com", "email3@gmail.com", "email4@gmail.com", "email5@gmail.com"})
    void usersServiceSuccessTest(String email) {
        Assertions.assertNotNull(usersService.signUp(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"email1@gmail.com", "email2@gmail.com", "email3@gmail.com", "email4@gmail.com", "email5@gmail.com"})
    void usersServiceExceptionTest(String email) {
        usersService.signUp(email);
        Assertions.assertThrowsExactly(RuntimeException.class, () -> usersService.signUp(email));
    }

    @Test
    void usersServiceNullTest() {
        Assertions.assertThrowsExactly(RuntimeException.class, () ->usersService.signUp(null));
    }
}
