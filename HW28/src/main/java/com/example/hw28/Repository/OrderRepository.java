package com.example.hw28.Repository;

import com.example.hw28.Model.Order;
import com.example.hw28.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Order findOrderById(Integer id);

    List<Order> findOrdersByCustomerContaining(User user);

    List<Order> findOrderByCustomerId(Integer id);
}
