package pl.wsb.fitnesstracker.user.api;

/**
 * Data Transfer Object representing a user with only their ID and email address.
 * <p>
 * Typically used in scenarios where only the user's email is needed, such as
 * search results or contact lists.
 *
 * @param id    unique identifier of the user
 * @param email email address of the user
 */
public record EmailUserDto(Long id, String email) {
}
