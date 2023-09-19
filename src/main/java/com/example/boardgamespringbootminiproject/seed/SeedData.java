package com.example.boardgamespringbootminiproject.seed;

import com.example.boardgamespringbootminiproject.repository.BoardgameRepository;
import com.example.boardgamespringbootminiproject.repository.CategoryRepository;
import com.example.boardgamespringbootminiproject.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * This class is for storing data for testing purposes and should not be present for production.
 */
@Component
public class SeedData implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final BoardgameRepository boardgameRepository;

    public SeedData(@Lazy PasswordEncoder passwordEncoder,
                    UserRepository userRepository,
                    CategoryRepository categoryRepository,
                    BoardgameRepository boardgameRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.boardgameRepository = boardgameRepository;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
