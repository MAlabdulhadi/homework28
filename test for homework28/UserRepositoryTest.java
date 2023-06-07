package com.example.hw28;


import com.example.hw28.Model.User;
import com.example.hw28.Repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    User user;

    @BeforeEach
    void setUp() {
        user = new User(null, "mohammed", "1234", "CUSTOMER", null);
    }

    @Test
    public void findUserByIdTest() {
        userRepository.save(user);
        User myUser = userRepository.findUserById(user.getId());
        Assertions.assertThat(myUser).isEqualTo(user);
    }

    @Test
    public void findUserByUsernameTest() {
        userRepository.save(user);
        User myUser = userRepository.findUserByUsername(user.getUsername());
        Assertions.assertThat(myUser).isEqualTo(user);
    }

}
