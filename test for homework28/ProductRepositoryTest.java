package com.example.hw28;

import com.example.hw28.Model.Product;
import com.example.hw28.Repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {


    @Autowired
    ProductRepository productRepository;

    Product product;

    @BeforeEach
    void setUp() {
        product = new Product(null, "coffees", 30.0, null);
    }

    @Test
    public void findProductByIdTest() {
        productRepository.save(product);
        Product myProduct = productRepository.findProductById(product.getId());
        Assertions.assertThat(myProduct).isEqualTo(product);
    }
}
