package com.example.boardgamespringbootminiproject.repository;

import com.example.boardgamespringbootminiproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A repository interface for accessing and managing user data in the database.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Check if a user with the specified email address already exists.
     *
     * @param userEmailAddress The email address to check for existence.
     * @return `true` if a user with the email address exists, `false` otherwise.
     */
    boolean existsByEmailAddress(String userEmailAddress);

    /**
     * Find a user by their email address.
     *
     * @param emailAddress The email address of the user to find.
     * @return The user with the specified email address, or `null` if not found.
     */
    User findUserByEmailAddress(String emailAddress);
}
