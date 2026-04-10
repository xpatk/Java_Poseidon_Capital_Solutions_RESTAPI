package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer responsible for handling business logic related to User entity.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * Constructor-based dependency injection of UserRepository.
     *
     * @param userRepository repository for User persistence operations
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves all users from the database.
     *
     * @return list of User entities
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Saves a new user or updates an existing one.
     * Password is encoded before being persisted.
     *
     * @param user the User entity to save
     * @return saved User entity
     */
    public User saveUser(User user) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // UPDATE
        if (user.getId() != null) {

            User existingUser = userRepository.findById(user.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + user.getId()));

            // if no password - dont change anything
            if (user.getPassword() == null || user.getPassword().isBlank()) {
                user.setPassword(existingUser.getPassword());
            } else {
                user.setPassword(encoder.encode(user.getPassword()));
            }

        } else {
            // encode and create
            user.setPassword(encoder.encode(user.getPassword()));
        }

        return userRepository.save(user);
    }

    /**
     * Retrieves a user by its identifier.
     *
     * @param id the User identifier
     * @return found User entity
     * @throws IllegalArgumentException if user is not found
     */
    public User findUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    }

    /**
     * Returns a user based on username.
     *
     * @param username the username of the user
     * @return User if found, otherwise null
     */
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    /**
     * Deletes a user by its identifier.
     *
     * @param id the User identifier
     */
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}