package pl.wsb.fitnesstracker.user.api;

/**
 * A simplified Data Transfer Object representing a user.
 * Contains only basic identification fields intended for lightweight views,
 * lists, or scenarios where full user details are unnecessary.
 *
 * @param id        unique identifier of the user
 * @param firstName user's first name
 * @param lastName  user's last name
 */
public record SimpleUserDto(Long id, String firstName, String lastName) {

}
