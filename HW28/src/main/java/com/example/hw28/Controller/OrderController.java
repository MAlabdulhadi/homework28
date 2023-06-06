package com.example.hw28.Controller;

import com.example.hw28.DTO.OrderDTO;
import com.example.hw28.Model.Order;
import com.example.hw28.Model.User;
import com.example.hw28.Service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PutMapping("/change-status/{orderId}/{status}")
    public ResponseEntity changeStatus(@PathVariable Integer orderId, @Valid @PathVariable String status) {
        orderService.changeStatus(orderId, status);
        return ResponseEntity.status(200).body("done change");
    }

    @PostMapping("/add-order")
    public ResponseEntity requestOrderForCustomer(@AuthenticationPrincipal User user, @RequestBody @Valid OrderDTO dto) {
        orderService.requestOrderForCustomer(user.getId(), dto);
        return ResponseEntity.status(200).body("order add");
    }


}
