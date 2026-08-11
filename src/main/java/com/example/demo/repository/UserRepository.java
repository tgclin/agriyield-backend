package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Existing query methods
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

    // Password reset query method
    Optional<User> findByResetToken(String resetToken);

    // Case-insensitive email handling methods
    Optional<User> findByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCase(String email);
}