package net.jfsanchez.hexdemo.infra.adapter.input.http.api;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.serde.annotation.SerdeImport;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import net.jfsanchez.hexdemo.domain.port.input.UserUseCase;
import net.jfsanchez.hexdemo.domain.valueobject.Page;
import net.jfsanchez.hexdemo.domain.valueobject.Pagination;
import net.jfsanchez.hexdemo.infra.adapter.input.http.api.dto.ApiUser;
import net.jfsanchez.hexdemo.infra.adapter.input.http.api.dto.CreateUserRequest;
import net.jfsanchez.hexdemo.infra.adapter.input.http.api.dto.CreateUserResponse;

@RequiredArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Controller(UserController.Paths.ROOT_PATH)
@SerdeImport(value = Page.class, deserializable = false)
@SerdeImport(value = Pagination.class, deserializable = false)
public class UserController {
    private final UserUseCase userUseCase;

    @Post
    public CreateUserResponse createUser(@Body CreateUserRequest request) {
        final var command = UserUseCase.RegisterUserCommand.of(
            request.name(),
            request.age(),
            request.maritalStatus()
        );
        final var registeredUser = userUseCase.register(command);
        return CreateUserResponse.builder()
            .id(registeredUser.uuid())
            .build();
    }

    @Get(uri = Paths.GET_USER_BY_ID_PATH)
    public Optional<ApiUser> getUserByIdUser(@PathVariable("id") UUID id) {
        return userUseCase.findById(new UserUseCase.FindUserByIdQuery(id)).map(ApiUser::of);
    }

    @Get
    public Page<ApiUser> listUsers(
        @QueryValue(value = "page", defaultValue = Pagination.DEFAULT_PAGE_NUMBER + "") Optional<Integer> page,
        @QueryValue(value = "size", defaultValue = Pagination.DEFAULT_PAGE_SIZE + "") Optional<Integer> size
    ) {
        final var pagination = Pagination.of(page.orElse(Pagination.DEFAULT_PAGE_NUMBER), size.orElse(Pagination.DEFAULT_PAGE_SIZE));
        final var usersPage = userUseCase.list(new UserUseCase.ListUserQuery(pagination));
        return Page.of(
            usersPage.items().stream().map(ApiUser::of).toList(),
            usersPage.totalSize(),
            pagination
        );
    }

    static final class Paths {
        public static final String ROOT_PATH = "/api/user";
        public static final String GET_USER_BY_ID_PATH = "/{id}";
    }
}
