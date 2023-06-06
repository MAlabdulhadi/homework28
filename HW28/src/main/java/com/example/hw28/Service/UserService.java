package com.example.hw28.Service;


import com.example.hw28.ApiException.ApiException;
import com.example.hw28.Model.User;
import com.example.hw28.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public User getCustomerById(Integer customerId) {
        User user = userRepository.findUserById(customerId);
        if (user == null) {
            throw new ApiException("do not have any user by this id");
        }
        return user;
    }


    public void register(User user) {
        String hashPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashPassword);
        user.setRole("CUSTOMER");
        userRepository.save(user);
    }

    public void updateCustomer(Integer customerId, User user) {
        User oldCustomer = userRepository.findUserById(customerId);
        oldCustomer.setUsername(user.getUsername());
        String hashPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        oldCustomer.setPassword(hashPassword);
        userRepository.save(oldCustomer);
    }

    public void deleteCustomer(Integer customerId) {
        User user = userRepository.findUserById(customerId);
        userRepository.delete(user);
    }

    public List<User> getAllCustomer() {
        return userRepository.findAll();
    }

}
