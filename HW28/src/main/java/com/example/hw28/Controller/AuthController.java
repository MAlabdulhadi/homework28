package com.example.hw28.Controller;

import com.example.hw28.Model.User;
import com.example.hw28.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User user) {
        userService.register(user);
        return ResponseEntity.status(200).body("done register");
    }


}
