package net.jfsanchez.hexdemo.domain.port.input.impl;

import java.util.Optional;
import java.util.UUID;
import java.util.random.RandomGenerator;
import net.jfsanchez.hexdemo.domain.entity.User;
import net.jfsanchez.hexdemo.domain.port.input.UserUseCase;
import net.jfsanchez.hexdemo.domain.port.output.EventPublisher;
import net.jfsanchez.hexdemo.domain.port.output.UserRepositoryPort;
import net.jfsanchez.hexdemo.domain.valueobject.MaritalStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserUseCaseImplTest {
    @Mock
    private UserRepositoryPort repository;
    @Mock
    private EventPublisher eventPublisher;
    @InjectMocks
    private UserUseCaseImpl useCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("register saves the User in the repository and returns the saved User")
    void register_saves_in_repository_and_returns_the_user() {
        final var command = UserUseCase.RegisterUserCommand.of(
            UUID.randomUUID().toString(),
            RandomGenerator.getDefault().nextInt(20, 100),
            MaritalStatus.SINGLE
        );

        when(repository.save(any())).thenAnswer(invocation -> invocation.getArgument(0, User.class));
        doNothing().when(eventPublisher).publish(any());

        final var user = useCase.register(command);
        Assertions.assertNotNull(user.uuid());
        Assertions.assertEquals(command.name(), user.name());
        Assertions.assertEquals(command.age(), user.age());
        Assertions.assertEquals(command.maritalStatus(), user.maritalStatus());
        verify(eventPublisher).publish(any());
    }

    @Test
    @DisplayName("findById returns empty when the User does not exist")
    void findById_returns_empty_when_user_does_not_exist() {
        final var query = new UserUseCase.FindUserByIdQuery(UUID.randomUUID());
        when(repository.findById(query.uuid())).thenReturn(Optional.empty());

        final var user = useCase.findById(query);
        Assertions.assertTrue(user.isEmpty());
    }

    @Test
    @DisplayName("findById returns the found User")
    void findById_returns_user_when_found() {
        final var query = new UserUseCase.FindUserByIdQuery(UUID.randomUUID());
        final var expected = new User(
            UUID.randomUUID(),
            UUID.randomUUID().toString(),
            RandomGenerator.getDefault().nextInt(20, 100),
            MaritalStatus.SINGLE
        );
        when(repository.findById(query.uuid())).thenReturn(Optional.of(expected));

        final var user = useCase.findById(query);
        Assertions.assertEquals(expected, user.get());
    }
}
