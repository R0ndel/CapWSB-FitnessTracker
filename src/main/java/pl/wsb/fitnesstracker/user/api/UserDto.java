package pl.wsb.fitnesstracker.user.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDate;

/**
 * Data Transfer Object representing detailed user information.
 * Used primarily for API communication, serialization and deserialization.
 * Unlike {@code SimpleUserDto}, includes birthdate and email.
 *
 * @param id        optional unique identifier of the user; may be {@code null} for newly created users
 * @param firstName user's first name
 * @param lastName  user's last name
 * @param birthdate user's birth date formatted as yyyy-MM-dd
 * @param email     user's email address
 */
public record UserDto(@Nullable Long id, String firstName, String lastName,
                      @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
                      String email) {

}
