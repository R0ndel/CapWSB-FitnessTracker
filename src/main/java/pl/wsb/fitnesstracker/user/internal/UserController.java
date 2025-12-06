package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.SimpleUserDto;

import java.util.List;
import pl.wsb.fitnesstracker.user.api.UserNotFoundException;

/**
 * UserController is responsible for handling HTTP requests related to user operations.
 * It provides endpoints for retrieving and creating users.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/simple")
    public List<SimpleUserDto> getSimpleUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable("id") Long id) {
        return userService.getUser(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @GetMapping("email")
    public UserDto getUserByEmail(@RequestParam("email") String email) {
        return userService.getUserByEmail(email)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(email, true));
    }

    @GetMapping("name")
    public UserDto getUserByName(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName) {
        return userService.getUserByName(firstName, lastName)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(firstName, lastName));
    }
}

