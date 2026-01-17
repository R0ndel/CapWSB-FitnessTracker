package pl.wsb.fitnesstracker.user.api;

import java.time.LocalDate;

/**
 * Request object used for updating an existing user's information.
 * <p>
 * Only non-null fields will be applied during the update. Fields that are {@code null}
 * will be ignored, leaving the existing user values unchanged.
 *
 * @param firstName new first name of the user, or {@code null} to leave unchanged
 * @param lastName  new last name of the user, or {@code null} to leave unchanged
 * @param email     new email of the user, or {@code null} to leave unchanged
 * @param birthdate new birth date of the user, or {@code null} to leave unchanged
 */
public record UpdateUserRequest(
        String firstName,
        String lastName,
        String email,
        LocalDate birthdate
) {}
