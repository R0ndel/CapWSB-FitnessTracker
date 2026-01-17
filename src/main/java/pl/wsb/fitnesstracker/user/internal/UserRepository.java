package pl.wsb.fitnesstracker.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.List;
import java.time.LocalDate;

/**
 * Repository interface for managing {@link User} entities.
 * Extends {@link JpaRepository} to provide CRUD operations and custom queries
 * for the {@code users} table.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByEmailContainingIgnoreCase(String email);

    List<User> findByBirthdateBefore(LocalDate date);
}
