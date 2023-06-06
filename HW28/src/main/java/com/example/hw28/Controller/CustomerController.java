package com.example.hw28.Controller;


import com.example.hw28.DTO.OrderDTO;
import com.example.hw28.Model.Order;
import com.example.hw28.Model.User;
import com.example.hw28.Service.OrderService;
import com.example.hw28.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final UserService userService;
    private final OrderService orderService;

    @GetMapping("/get-order")
    public ResponseEntity getOrdersCustomer(@AuthenticationPrincipal User user) {
        List<Order> orderList = orderService.getOrderCustomer(user.getId());
        return ResponseEntity.status(200).body(orderList);
    }

    @PostMapping("/update")
    public ResponseEntity updateCustomer(@AuthenticationPrincipal User user, @Valid @RequestBody User newUser) {
        userService.updateCustomer(user.getId(), newUser);
        return ResponseEntity.status(200).body("done updated");
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteCustomer(@AuthenticationPrincipal User user) {
        userService.deleteCustomer(user.getId());
        return ResponseEntity.status(200).body("done deleted!");
    }

    @DeleteMapping("/delete-order/{orderId}")
    public ResponseEntity deleteOrder(@AuthenticationPrincipal User user, @PathVariable Integer orderId) {
        orderService.deleteOrder(user.getId(), orderId);
        return ResponseEntity.status(200).body("deleted order");
    }

    @GetMapping("/get-order-by-id/{orderId}")
    public ResponseEntity getOrderById(@AuthenticationPrincipal User user, @PathVariable Integer orderId) {
        Order order = orderService.getOrderById(user.getId(), orderId);
        return ResponseEntity.status(200).body(order);
    }

    @GetMapping("/get-customer-by-id/{customerId}")
    public ResponseEntity getCustomerById(@PathVariable Integer customerId) {
        User user = userService.getCustomerById(customerId);
        return ResponseEntity.status(200).body(user);
    }

    @GetMapping("/get-all-customer")
    public ResponseEntity getAllCustomer() {
        List<User> customers = userService.getAllCustomer();
        return ResponseEntity.status(200).body(customers);
    }


}
