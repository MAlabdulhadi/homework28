package com.example.hw28;

import com.example.hw28.Model.User;
import com.example.hw28.Repository.UserRepository;
import com.example.hw28.Service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {


    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;

    User user, user1, user2;
    List<User> userList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        user = new User(null, "mohammed", "1234", "CUSTOMER", null);
        user1 = new User(null, "saad", "1234", "CUSTOMER", null);
        user2 = new User(null, "Ali", "1234", "CUSTOMER", null);
        userList.add(user);
        userList.add(user1);
        userList.add(user2);
    }

    @Test
    void getAllCustomerTest() {

        when(userRepository.findAll()).thenReturn(userList);
        List<User> users = userService.getAllCustomer();
        Assertions.assertEquals(users, userList);
        verify(userRepository, times(1)).findAll();

    }

    @Test
    void getCustomerByIdTest() {
        when(userRepository.findUserById(user.getId())).thenReturn(user);
        User customer = userService.getCustomerById(user.getId());
        Assertions.assertEquals(customer, user);
        verify(userRepository, times(1)).findUserById(user.getId());

    }

    @Test
    void updateCustomerTest() {
        when(userRepository.findUserById(user.getId())).thenReturn(user);
        userService.updateCustomer(user.getId(), user2);
        verify(userRepository, times(1)).findUserById(user.getId());
    }

    @Test
    void deleteCustomerTest() {
        when(userRepository.findUserById(user.getId())).thenReturn(user);
        userService.deleteCustomer(user.getId());
        verify(userRepository, times(1)).findUserById(user.getId());

    }


}
