package com.example.boardgamespringbootminiproject.seed;

import com.example.boardgamespringbootminiproject.model.Boardgame;
import com.example.boardgamespringbootminiproject.model.Category;
import com.example.boardgamespringbootminiproject.model.User;
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
        User user = new User();
        user.setUserName("liz");
        user.setEmailAddress("liz@ga.com");
        user.setPassword(passwordEncoder.encode("liz123"));
        userRepository.save(user);

        Category category1 = new Category();
        category1.setName("campaign");
        category1.setUser(user);
        categoryRepository.save(category1);

        Category category2 = new Category();
        category2.setName("card");
        category2.setUser(user);
        categoryRepository.save(category2);

        Boardgame boardgame1 = new Boardgame();
        boardgame1.setName("charterstone");
        boardgame1.setPlayers(1-6);
        boardgame1.setTime(60);
        boardgame1.setCategory(category1);
        boardgame1.setUser(user);
        boardgameRepository.save(boardgame1);

        Boardgame boardgame2 = new Boardgame();
        boardgame2.setName("gloomhaven");
        boardgame2.setPlayers(1-4);
        boardgame2.setTime(120);
        boardgame2.setCategory(category1);
        boardgame2.setUser(user);
        boardgameRepository.save(boardgame2);

        Boardgame boardgame3 = new Boardgame();
        boardgame3.setName("wingspan");
        boardgame3.setPlayers(1-5);
        boardgame3.setTime(70);
        boardgame3.setCategory(category2);
        boardgame3.setUser(user);
        boardgameRepository.save(boardgame3);
    }
}
