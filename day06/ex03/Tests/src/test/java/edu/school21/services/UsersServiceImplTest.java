package edu.school21.services;

import edu.school21.repositories.UsersRepository;
import edu.school21.models.User;
import edu.school21.services.UserServiceImpl;
import edu.school21.exceptions.AlreadyAuthenticatedException;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsersServiceImplTest {
    private final String RIGHT_LOGIN = "right_login";
    private final String WRONG_LOGIN = "wrong_login";
    private final String RIGHT_PASS = "right_password";
    private final String WRONG_PASS = "wrong_password";

    private User user;
    private UsersRepository usersRepositoryMock = Mockito.mock(UsersRepository.class);
    private UserServiceImpl usersServiceMock = new UserServiceImpl(usersRepositoryMock);

    @BeforeEach
    void beforeEach() {
        user = new User(3, RIGHT_LOGIN, RIGHT_PASS, false);
        Mockito.when(usersRepositoryMock.findByLogin(RIGHT_LOGIN)).thenReturn(user);
        Mockito.when(usersRepositoryMock.findByLogin(WRONG_LOGIN)).thenReturn(null);
    }

    @Test
    void authenticateEntityNotFoundExceptionTest() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> usersServiceMock.authenticate(WRONG_LOGIN, WRONG_PASS));
    }

    @Test
    void authenticateAlreadyAuthenticatedTest() {
        User user1 = user;
        user1.setAuthenticated(true);
        Assertions.assertThrows(AlreadyAuthenticatedException.class, () -> usersServiceMock.authenticate(RIGHT_LOGIN, RIGHT_PASS));
    }

    @Test
    void authenticateFalseWrongPassTest() throws Exception {
        Assertions.assertFalse(usersServiceMock.authenticate(RIGHT_LOGIN, WRONG_PASS));
    }

    @Test
    void authenticateTrueWrongPassTest() throws Exception {
        Assertions.assertTrue(usersServiceMock.authenticate(RIGHT_LOGIN, RIGHT_PASS));
    }
}
