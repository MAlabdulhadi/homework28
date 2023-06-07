package com.example.hw28;

import com.example.hw28.Model.Product;
import com.example.hw28.Repository.ProductRepository;
import com.example.hw28.Service.ProductService;
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
public class ProductServiceTest {

    @InjectMocks
    ProductService productService;
    @Mock
    ProductRepository productRepository;

    Product product, product1, product2;
    List<Product> productList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        product = new Product(null, "test", 5.0, null);
        product1 = new Product(null, "test1", 5.0, null);
        product2 = new Product(null, "test2", 5.0, null);
        productList.add(product);
        productList.add(product1);
        productList.add(product2);
    }

    @Test
    void getAllProductTest() {
        when(productRepository.findAll()).thenReturn(productList);
        List<Product> products = productService.getAllProduct();
        Assertions.assertEquals(products, productList);
        verify(productRepository, times(1)).findAll();
    }
}
