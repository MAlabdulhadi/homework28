package com.example.hw28.Service;

import com.example.hw28.ApiException.ApiException;
import com.example.hw28.DTO.OrderDTO;
import com.example.hw28.Model.Order;
import com.example.hw28.Model.Product;
import com.example.hw28.Model.User;
import com.example.hw28.Repository.OrderRepository;
import com.example.hw28.Repository.ProductRepository;
import com.example.hw28.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public List<Order> getOrderCustomer(Integer id) {
        User user = userRepository.findUserById(id);
        List<Order> orders = orderRepository.findOrderByCustomerId(id);
        if (orders.isEmpty()) {
            throw new ApiException("do not have any order for you ");
        }
        return orders;
    }

    public void requestOrderForCustomer(Integer customerId, OrderDTO dto) {
        Product product = productRepository.findProductById(dto.getProductId());
        User user = userRepository.findUserById(customerId);
        Double totalPrice = product.getPrice() * dto.getQuantity();
        Order newOrder = new Order(null, dto.getQuantity(), totalPrice, new Date(), "new", user, product);
        orderRepository.save(newOrder);
    }

    public void deleteOrder(Integer customerId, Integer orderId) {
        Order order = orderRepository.findOrderById(orderId);
        if (order.getCustomer().getId() != customerId) {
            throw new ApiException("do not have order by this id");
        }
        if (order.getStatus().equalsIgnoreCase("inProgress") || order.getStatus().equalsIgnoreCase("completed")) {
            throw new ApiException("can't delete this order because inProgress or completed ");
        }
        order.setCustomer(null);
        orderRepository.delete(order);
    }

    public void changeStatus(Integer orderId, String status) {
        Order order = orderRepository.findOrderById(orderId);
        order.setStatus(status);
        orderRepository.save(order);
    }

    public Order getOrderById(Integer customerId, Integer orderId) {
        Order order = orderRepository.findOrderById(orderId);
        if (order.getCustomer().getId() != customerId) {
            throw new ApiException("do not have any order by this id");

        }
        return order;
    }
}
