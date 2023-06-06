package com.example.hw28.Service;

import com.example.hw28.ApiException.ApiException;
import com.example.hw28.Model.Order;
import com.example.hw28.Model.Product;
import com.example.hw28.Repository.OrderRepository;
import com.example.hw28.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;


    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }


    public void updateProduct(Integer idProduct, Product product) {
        Product oldProduct = productRepository.findProductById(idProduct);
        if (oldProduct == null) {
            throw new ApiException("do not have any product");
        }
        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());
        productRepository.save(oldProduct);
    }

    public void deleteProduct(Integer productId) {
        Product product = productRepository.findProductById(productId);
        if (product == null) {
            throw new ApiException("do not have any product");
        }
        productRepository.delete(product);
    }


}
