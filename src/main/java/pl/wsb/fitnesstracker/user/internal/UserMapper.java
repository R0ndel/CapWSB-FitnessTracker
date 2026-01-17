package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.EmailUserDto;
import pl.wsb.fitnesstracker.user.api.SimpleUserDto;

/**
 * Component responsible for mapping {@link User} entities
 * to corresponding Data Transfer Objects.
 *
 * <p>This mapper isolates conversion logic from business code and helps
 * keep controller and service layers clean.</p>
 */
@Component
class UserMapper {

    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }

    /**
     * Converts a {@link User} entity to a {@link SimpleUserDto}
     * containing only basic user information.
     *
     * @param user entity to convert; must not be {@code null}
     * @return populated {@link SimpleUserDto} instance
     */
    SimpleUserDto toSimpleDto(User user) {
        return new SimpleUserDto(user.getId(),
            user.getFirstName(),
            user.getLastName()
        );
    }

    EmailUserDto toEmailDto(User user) {
        return new EmailUserDto(
                user.getId(),
                user.getEmail()
        );
    }
}
