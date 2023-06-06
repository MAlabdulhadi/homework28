package com.example.demo.Service;


import com.example.demo.Model.MyUser;
import com.example.demo.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    public List<MyUser> getAllUser() {
        return authRepository.findAll();
    }

    public void register(MyUser myUser) {
        String hash = new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setPassword(hash);
        myUser.setRole("USER");
        authRepository.save(myUser);
    }


}
