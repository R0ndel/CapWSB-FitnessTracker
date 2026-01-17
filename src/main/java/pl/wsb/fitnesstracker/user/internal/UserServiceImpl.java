package pl.wsb.fitnesstracker.user.internal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
/**
 * Implementation of {@link UserService} and {@link UserProvider} that handles
 * business logic for user management.
 * <p>
 * Provides methods for creating, retrieving, updating, deleting, and searching users.
 */
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(final User user) {
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findAll()
                .stream()
                .filter(u -> email.equalsIgnoreCase(u.getEmail()))
                .findFirst();
    }

    public List<User> searchUsersByEmailFragment(String emailFragment) {
        return userRepository.findByEmailContainingIgnoreCase(emailFragment);
    }

    public List<User> findUsersOlderThan(int age) {
        LocalDate dateThreshold = LocalDate.now().minusYears(age);
        return userRepository.findByBirthdateBefore(dateThreshold);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, User updated) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setFirstName(updated.getFirstName());
                    user.setLastName(updated.getLastName());
                    user.setEmail(updated.getEmail());
                    user.setBirthdate(updated.getBirthdate());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
