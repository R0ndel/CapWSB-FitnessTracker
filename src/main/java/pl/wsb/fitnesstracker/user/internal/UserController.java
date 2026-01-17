package pl.wsb.fitnesstracker.user.internal;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.EmailUserDto;
import pl.wsb.fitnesstracker.user.api.SimpleUserDto;
import pl.wsb.fitnesstracker.user.api.UpdateUserRequest;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.List;

/**
 * UserController is responsible for handling HTTP requests related to user operations.
 * It provides endpoints for retrieving and creating users.
 */
@RestController
@RequestMapping("/v1/users")
class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    public UserController(UserServiceImpl userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    /**
     * Retrieves all users in full detail.
     *
     * @return list of {@link UserDto} representing all users
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Retrieves all users in a simplified form (without email or birthdate).
     *
     * @return list of {@link SimpleUserDto} representing all users
     */
    @GetMapping("/simple")
    public List<SimpleUserDto> getSimpleUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param id the ID of the user to retrieve
     * @return {@link UserDto} containing user details
     * @throws IllegalArgumentException if the user is not found
     */
    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userService.getUser(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    /**
     * Creates a new user.
     *
     * @param request the {@link User} object containing user data
     * @return {@link UserDto} representing the newly created user
     */
    @PostMapping
    public UserDto createUser(@RequestBody User request) {
        User createdUser = userService.createUser(request);
        return userMapper.toDto(createdUser);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }


    /**
     * Updates an existing user by their ID.
     * <p>
     * Only non-null fields in {@link UpdateUserRequest} will be updated.
     *
     * @param id      the ID of the user to update
     * @param request the {@link UpdateUserRequest} containing updated fields
     * @return {@link UserDto} representing the updated user
     * @throws IllegalArgumentException if the user is not found
     */
    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id,
                            @RequestBody UpdateUserRequest request) {

        User savedUser = userService.getUser(id)
                .map(user -> {
                    if (request.firstName() != null) user.setFirstName(request.firstName());
                    if (request.lastName() != null) user.setLastName(request.lastName());
                    if (request.email() != null) user.setEmail(request.email());
                    if (request.birthdate() != null) user.setBirthdate(request.birthdate());
                    return userService.updateUser(id, user);
                })
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return userMapper.toDto(savedUser);
    }

    
    /**
     * Searches for users whose email contains the specified fragment, case-insensitive.
     *
     * @param email fragment of the email to search for
     * @return list of {@link EmailUserDto} matching the search
     */
    @GetMapping("/search")
    public List<EmailUserDto> searchUsersByEmailFragment(@RequestParam String email) {
        return userService.searchUsersByEmailFragment(email)
                .stream()
                .map(userMapper::toEmailDto)
                .toList();
    }

    
    /**
     * Retrieves users older than a specified minimum age.
     *
     * @param min minimum age to filter users
     * @return list of {@link UserDto} representing users older than {@code min} years
     */
    @GetMapping("/age")
    public List<UserDto> getOlderThan(@RequestParam int min) {
        return userService.findUsersOlderThan(min)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
}
