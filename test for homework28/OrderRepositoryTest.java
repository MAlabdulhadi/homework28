package com.example.hw28;


import com.example.hw28.Model.Order;
import com.example.hw28.Model.User;
import com.example.hw28.Repository.OrderRepository;
import com.example.hw28.Repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderRepositoryTest {


    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;
    Order order, order2, order3;
    User customer;
    List<Order> orderList;


    @BeforeEach
    void setUp() {
        customer = new User(null, "saad", "3454545", "CUSTOMER", null);
        order = new Order(null, 5, 5.0, null, "new", customer, null);
        order2 = new Order(null, 6, 6.0, null, "new", customer, null);
        order3 = new Order(null, 7, 7.0, null, "new", customer, null);

    }

    @Test
    void findOrderByIdTest() {
        userRepository.save(customer);
        orderRepository.save(order);
        Order myOrder = orderRepository.findOrderById(order.getId());
        Assertions.assertThat(myOrder).isEqualTo(order);
    }

    @Test
    void findOrderByCustomerIdTest() {
        userRepository.save(customer);
        orderRepository.save(order);
        orderRepository.save(order2);
        orderRepository.save(order3);
        orderList = orderRepository.findOrderByCustomerId(customer.getId());
        Assertions.assertThat(orderList.get(0).getCustomer().getId()).isEqualTo(customer.getId());

    }
}
